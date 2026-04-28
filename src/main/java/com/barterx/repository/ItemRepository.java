package com.barterx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.barterx.model.Item;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByOwnerId(int ownerId);
}