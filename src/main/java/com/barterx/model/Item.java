package com.barterx.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String category;
    private String imageUrl;

    @Column(name = "item_condition")
    private String itemCondition;

    private String wantInExchange;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCondition() { return itemCondition; }
    public void setCondition(String condition) { this.itemCondition = condition; }

    public String getWantInExchange() { return wantInExchange; }
    public void setWantInExchange(String wantInExchange) { this.wantInExchange = wantInExchange; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}