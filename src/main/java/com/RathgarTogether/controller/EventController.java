package com.RathgarTogether.controller;

import com.RathgarTogether.dto.EventDto;
import com.RathgarTogether.dto.HobbyGroupDto;
import com.RathgarTogether.entities.Event;
import com.RathgarTogether.entities.HobbyGroup;
import com.RathgarTogether.service.event.EventService;
import com.RathgarTogether.service.hobbyGroup.HobbyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {


    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) {

        Event event = eventService.createEvent(eventDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @GetMapping("/{eventId}/member/{memberId}")
    public ResponseEntity<?> addMemberToEvent(@PathVariable Long eventId, @PathVariable Long memberId) {
        Event event = eventService.addMemberToEvent(eventId, memberId);
        if (event == null) {
            // Return a custom error response when the member is already present in the hobby group
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Member is already present in the event.");
        }

        return ResponseEntity.ok(event);

    }
}
