package com.RathgarTogether.service.hobbyGroup;

import com.RathgarTogether.dto.HobbyGroupDto;
import com.RathgarTogether.dto.HobbyGroupPageDto;
import com.RathgarTogether.entities.Comment;
import com.RathgarTogether.entities.Event;
import com.RathgarTogether.entities.HobbyGroup;
import com.RathgarTogether.entities.User;
import com.RathgarTogether.repo.CommentRepository;
import com.RathgarTogether.repo.EventRepository;
import com.RathgarTogether.repo.HobbyGroupRepository;
import com.RathgarTogether.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HobbyGroupServiceImpl implements HobbyGroupService{

    @Autowired
    private HobbyGroupRepository hobbyGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    public HobbyGroup createHobbyGroup(HobbyGroupDto hobbyGroupDto) {
        Optional<User> optionalUser = userRepository.findById(hobbyGroupDto.getOwner());
        if(optionalUser.isPresent()){
            HobbyGroup hobbyGroup = new HobbyGroup();
            hobbyGroup.setName(hobbyGroupDto.getName());
            hobbyGroup.setOwner(optionalUser.get());
            hobbyGroup.setSpeciality(hobbyGroupDto.getSpeciality());

            return hobbyGroupRepository.save(hobbyGroup);
        }

        return null;
    }

    public List<HobbyGroup> getAllHobbyGroups() {
        return hobbyGroupRepository.findAll();
    }

    public HobbyGroup addMemberToHobbyGroup(Long groupId, Long memberId) {
        HobbyGroup hobbyGroup = hobbyGroupRepository.findById(groupId).orElse(null);
        User member = userRepository.findById(memberId).orElse(null);

        if (hobbyGroup != null && member != null) {
            if (hobbyGroup.getMembers().contains(member)) {
               return null;
            }
            hobbyGroup.getMembers().add(member);
            return hobbyGroupRepository.save(hobbyGroup);
        }

        return null;
    }


    public List<HobbyGroup> getHobbyGroupsByUserId(Long userId) {
        // Get hobby groups where the user is the owner
        List<HobbyGroup> ownedGroups = hobbyGroupRepository.findByOwnerId(userId);

        // Get hobby groups where the user is a member
        List<HobbyGroup> memberGroups = hobbyGroupRepository.findByMembersId(userId);

        // Combine the lists to ensure unique hobby groups
        Set<HobbyGroup> uniqueHobbyGroups = new HashSet<>(ownedGroups);
        uniqueHobbyGroups.addAll(memberGroups);

        return new ArrayList<>(uniqueHobbyGroups);
    }

    public HobbyGroupPageDto getHobbyGroupById(Long groupId) {
        HobbyGroupPageDto hobbyGroupPageDto = new HobbyGroupPageDto();
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        List<Comment> commentList = commentRepository.findAllByHobbyGroupId(groupId,sort);
        Date currentDate = new Date();
        Sort sortEvent = Sort.by(Sort.Direction.ASC, "date");
        List<Event> eventList = eventRepository.findAllByHobbyGroupIdAndDateAfter(groupId, currentDate ,sortEvent);
        hobbyGroupPageDto.setCommentList(commentList);
        hobbyGroupPageDto.setEventList(eventList);
        hobbyGroupPageDto.setHobbyGroup(hobbyGroupRepository.findById(groupId).orElse(null));

        return hobbyGroupPageDto;
    }
}
