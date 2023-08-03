package com.RathgarTogether.service.event;

import com.RathgarTogether.dto.EventDto;
import com.RathgarTogether.dto.HobbyGroupDto;
import com.RathgarTogether.entities.Event;
import com.RathgarTogether.entities.HobbyGroup;
import com.RathgarTogether.entities.User;
import com.RathgarTogether.repo.EventRepository;
import com.RathgarTogether.repo.HobbyGroupRepository;
import com.RathgarTogether.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private HobbyGroupRepository hobbyGroupRepository;

    @Autowired
    private UserRepository userRepository;

    public Event createEvent(EventDto eventDto) {
        Optional<HobbyGroup> optionalHobbyGroup = hobbyGroupRepository.findById(eventDto.getHobbyGroupId());
        if(optionalHobbyGroup.isPresent()){
            Event event = new Event();
            event.setTitle(eventDto.getTitle());
            event.setBody(eventDto.getBody());
            event.setDate(eventDto.getDate());
            event.setHobbyGroup(optionalHobbyGroup.get());

            return eventRepository.save(event);
        }

        return null;
    }

    public Event addMemberToEvent(Long eventId, Long memberId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        User member = userRepository.findById(memberId).orElse(null);

        if (event != null && member != null) {
            if (event.getMembers().contains(member)) {
                return null;
            }
            event.getMembers().add(member);
            return eventRepository.save(event);
        }

        return null;
    }
}
