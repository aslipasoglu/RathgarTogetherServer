package com.RathgarTogether.controller;

import com.RathgarTogether.dto.HobbyGroupDto;
import com.RathgarTogether.dto.HobbyGroupPageDto;
import com.RathgarTogether.entities.HobbyGroup;
import com.RathgarTogether.service.hobbyGroup.HobbyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hobbyGroup")
public class HobbyGroupController {

    @Autowired
    private HobbyGroupService hobbyGroupService;

    @PostMapping
    public ResponseEntity<?> createHobbyGroup(@RequestBody HobbyGroupDto hobbyGroupDto) {

        HobbyGroup hobbyGroup = hobbyGroupService.createHobbyGroup(hobbyGroupDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(hobbyGroup);
    }

    @GetMapping
    public ResponseEntity<List<HobbyGroup>> getAllHobbyGroups() {
        List<HobbyGroup> hobbyGroups = hobbyGroupService.getAllHobbyGroups();
        return ResponseEntity.ok(hobbyGroups);
    }

    @GetMapping("/{groupId}/member/{memberId}")
    public ResponseEntity<?> addMemberToHobbyGroup(@PathVariable Long groupId, @PathVariable Long memberId) {
        HobbyGroup hobbyGroup = hobbyGroupService.addMemberToHobbyGroup(groupId, memberId);
        if (hobbyGroup == null) {
            // Return a custom error response when the member is already present in the hobby group
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Member is already present in the hobby group.");
        }

            return ResponseEntity.ok(hobbyGroup);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HobbyGroup>> getHobbyGroupsByUserId(@PathVariable Long userId) {
        List<HobbyGroup> hobbyGroups = hobbyGroupService.getHobbyGroupsByUserId(userId);

        if (hobbyGroups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(hobbyGroups, HttpStatus.OK);
        }
    }


    @GetMapping("/{groupId}")
    public ResponseEntity<HobbyGroupPageDto> getHobbyGroupById(@PathVariable Long groupId) {
        HobbyGroupPageDto hobbyGroup = hobbyGroupService.getHobbyGroupById(groupId);

        if (hobbyGroup == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(hobbyGroup, HttpStatus.OK);
        }
    }

//    @PostMapping("/search")
//    public ResponseEntity<?> searchBooking(@RequestBody BookingDto bookingDto) {
//
//        List<Booking> bookings = bookingService.getAllBookingsByDateAndUrbanisation(bookingDto);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(bookings);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
//        boolean deleted = bookingService.deleteBooking(id);
//
//        if (deleted) {
//            return ResponseEntity.ok("Booking deleted successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
