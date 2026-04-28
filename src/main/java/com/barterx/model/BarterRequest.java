package com.barterx.model;

import jakarta.persistence.*;

@Entity
@Table(name = "barter_requests")
public class BarterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User requester;

    @ManyToOne
    private Item itemRequested;

    @ManyToOne
    private Item itemOffered;

    private String status;

    private String meetingLocation;
    private String exchangeStatus; // PENDING, ACCEPTED, COMPLETED

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public User getRequester() { return requester; }
    public void setRequester(User requester) { this.requester = requester; }

    public Item getItemRequested() { return itemRequested; }
    public void setItemRequested(Item itemRequested) { this.itemRequested = itemRequested; }

    public Item getItemOffered() { return itemOffered; }
    public void setItemOffered(Item itemOffered) { this.itemOffered = itemOffered; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMeetingLocation() { return meetingLocation; }
    public void setMeetingLocation(String meetingLocation) { this.meetingLocation = meetingLocation; }

    public String getExchangeStatus() { return exchangeStatus; }
    public void setExchangeStatus(String exchangeStatus) { this.exchangeStatus = exchangeStatus; }
}