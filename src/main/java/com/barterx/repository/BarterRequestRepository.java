package com.barterx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.barterx.model.BarterRequest;

import java.util.List;

public interface BarterRequestRepository extends JpaRepository<BarterRequest, Integer> {

    @Query("SELECT b FROM BarterRequest b WHERE b.requester.id = :userId OR b.itemRequested.owner.id = :userId")
    List<BarterRequest> findRequestsInvolvingUser(@Param("userId") int userId);

    @Query("SELECT b FROM BarterRequest b WHERE b.itemRequested.id = :itemId OR b.itemOffered.id = :itemId")
    List<BarterRequest> findRequestsInvolvingItem(@Param("itemId") int itemId);
}