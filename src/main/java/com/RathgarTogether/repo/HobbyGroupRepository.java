package com.RathgarTogether.repo;

import com.RathgarTogether.entities.HobbyGroup;
import com.RathgarTogether.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyGroupRepository extends JpaRepository<HobbyGroup, Long> {

    List<HobbyGroup> findByOwnerId(Long ownerId);
    List<HobbyGroup> findByMembersId(Long memberId);
}
