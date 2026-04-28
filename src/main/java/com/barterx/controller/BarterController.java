package com.barterx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.barterx.model.BarterRequest;
import com.barterx.model.User;
import com.barterx.service.BarterService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/barter")
@CrossOrigin
public class BarterController {

    @Autowired
    private BarterService service;

    // SEND REQUEST
    @PostMapping("/request")
    public BarterRequest request(@RequestBody BarterRequest br) {
        return service.sendRequest(br);
    }

    // ACCEPT / REJECT
    @PutMapping("/{id}/{status}")
    public BarterRequest update(@PathVariable int id, @PathVariable String status) {
        return service.updateStatus(id, status);
    }

    // GET ALL REQUESTS
    @GetMapping
    public List<BarterRequest> getAllRequests() {
        return service.getAllRequests();
    }

    // GET REQUESTS INVOLVING A SPECIFIC USER
    @GetMapping("/user/{userId}")
    public List<BarterRequest> getRequestsByUser(@PathVariable int userId) {
        return service.getRequestsByUser(userId);
    }

    // GET CONTACT INFO FOR A REQUEST
    @GetMapping("/{id}/contact")
    public Map<String, Object> getContact(@PathVariable int id) {
        BarterRequest br = service.getRequestById(id);
        if (br == null) return null;

        Map<String, Object> response = new HashMap<>();
        response.put("requester", br.getRequester());
        
        User otherUser = null;
        if (br.getItemRequested() != null && br.getItemOffered() != null) {
            if ("ACCEPTED".equalsIgnoreCase(br.getStatus()) || "COMPLETED".equalsIgnoreCase(br.getStatus())) {
                otherUser = br.getItemOffered().getOwner();
            } else {
                otherUser = br.getItemRequested().getOwner();
            }
        }
        response.put("owner", otherUser);
        response.put("itemRequested", br.getItemRequested());
        response.put("itemOffered", br.getItemOffered());
        response.put("meetingLocation", br.getMeetingLocation());
        response.put("exchangeStatus", br.getExchangeStatus());

        return response;
    }

    // MARK EXCHANGE AS COMPLETED
    @PutMapping("/{id}/complete")
    public BarterRequest completeExchange(@PathVariable int id) {
        return service.completeExchange(id);
    }

    // UPDATE MEETING LOCATION
    @PutMapping("/{id}/meeting")
    public BarterRequest updateMeetingLocation(@PathVariable int id, @RequestBody Map<String, String> payload) {
        String location = payload.get("meetingLocation");
        return service.updateMeetingLocation(id, location);
    }
}