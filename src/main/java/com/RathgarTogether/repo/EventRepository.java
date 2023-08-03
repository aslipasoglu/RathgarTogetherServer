package com.RathgarTogether.repo;

import com.RathgarTogether.entities.Comment;
import com.RathgarTogether.entities.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository  extends JpaRepository<Event, Long> {

    List<Event> findAllByHobbyGroupId(Long id, Sort sort);

    List<Event> findAllByHobbyGroupIdAndDateAfter(Long id, Date currentDate, Sort sort);
}
