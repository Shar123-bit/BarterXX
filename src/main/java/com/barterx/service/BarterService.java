package com.barterx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barterx.model.*;
import com.barterx.repository.*;

import java.util.List;

@Service
public class BarterService {

    @Autowired
    private BarterRequestRepository repo;

    @Autowired
    private ItemRepository itemRepo;

    // CREATE REQUEST
    public BarterRequest sendRequest(BarterRequest request) {
        request.setStatus("PENDING");
        request.setExchangeStatus("PENDING");
        return repo.save(request);
    }

    // GET REQUEST BY ID
    public BarterRequest getRequestById(int id) {
        return repo.findById(id).orElse(null);
    }

    // GET ALL REQUESTS
    public List<BarterRequest> getAllRequests() {
        return repo.findAll();
    }

    // GET REQUESTS INVOLVING A SPECIFIC USER
    public List<BarterRequest> getRequestsByUser(int userId) {
        return repo.findRequestsInvolvingUser(userId);
    }

    // UPDATE STATUS (ACCEPT / REJECT)
    public BarterRequest updateStatus(int id, String status) {

        BarterRequest br = repo.findById(id).orElse(null);

        if (br == null) {
            return null;
        }

        if ("ACCEPTED".equalsIgnoreCase(br.getStatus())) {
            return br; // Already accepted, prevent re-swapping
        }

        // ONLY SWAP IF ACCEPTED
        if (status.equalsIgnoreCase("ACCEPTED")) {

            Item requested = br.getItemRequested();
            Item offered = br.getItemOffered();

            if (requested != null && offered != null) {

                User owner1 = requested.getOwner();
                User owner2 = offered.getOwner();

                // SWAP OWNERS
                requested.setOwner(owner2);
                offered.setOwner(owner1);

                itemRepo.save(requested);
                itemRepo.save(offered);
            }
        }

        // UPDATE STATUS
        br.setStatus(status.toUpperCase());
        return repo.save(br);
    }

    // UPDATE EXCHANGE TO COMPLETED
    public BarterRequest completeExchange(int id) {
        BarterRequest br = repo.findById(id).orElse(null);
        if (br != null) {
            br.setExchangeStatus("COMPLETED");
            return repo.save(br);
        }
        return null;
    }

    // UPDATE MEETING LOCATION
    public BarterRequest updateMeetingLocation(int id, String location) {
        BarterRequest br = repo.findById(id).orElse(null);
        if (br != null) {
            br.setMeetingLocation(location);
            return repo.save(br);
        }
        return null;
    }
}