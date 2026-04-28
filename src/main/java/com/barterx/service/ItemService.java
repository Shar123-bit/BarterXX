package com.barterx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barterx.model.Item;
import com.barterx.model.BarterRequest;
import com.barterx.repository.ItemRepository;
import com.barterx.repository.BarterRequestRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;

    @Autowired
    private BarterRequestRepository barterRepo;

    public Item addItem(Item item) {
        return repo.save(item);
    }

    public List<Item> getAllItems() {
        return repo.findAll();
    }

    public List<Item> getItemsByUser(int userId) {
        return repo.findByOwnerId(userId);
    }

    public Item getItemById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteItem(int id) {
        List<BarterRequest> relatedRequests = barterRepo.findRequestsInvolvingItem(id);
        if (!relatedRequests.isEmpty()) {
            barterRepo.deleteAll(relatedRequests);
        }
        repo.deleteById(id);
    }
}