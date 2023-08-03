package com.RathgarTogether.service.hobbyGroup;

import com.RathgarTogether.dto.HobbyGroupDto;
import com.RathgarTogether.dto.HobbyGroupPageDto;
import com.RathgarTogether.entities.HobbyGroup;

import java.util.List;

public interface HobbyGroupService {

    HobbyGroup createHobbyGroup(HobbyGroupDto hobbyGroupDto);

    HobbyGroup addMemberToHobbyGroup(Long groupId, Long memberId);

    List<HobbyGroup> getAllHobbyGroups();

    List<HobbyGroup> getHobbyGroupsByUserId(Long userId);

    HobbyGroupPageDto getHobbyGroupById(Long groupId);
}
