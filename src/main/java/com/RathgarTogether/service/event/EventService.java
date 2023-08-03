package com.RathgarTogether.service.event;

import com.RathgarTogether.dto.EventDto;
import com.RathgarTogether.entities.Event;

public interface EventService {

    Event createEvent(EventDto eventDto);

    Event addMemberToEvent(Long eventId, Long memberId);
}
