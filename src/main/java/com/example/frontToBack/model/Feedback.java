package com.example.frontToBack.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long feedbackId;

    @Column(name = "item_id")
    @NotBlank
    private Long itemId;

    @Column(name = "interested_id")
    @NotBlank
    private Long userId;

    @Column(name = "rating")
    @NotBlank
    private float rating;

    @Column(name = "hash_key")
    @NotBlank
    private String hashKey;

    public Feedback() {
    }

    public Feedback(Long feedbackId, Long itemId, Long interestedId) {
        this.feedbackId = feedbackId;
        this.itemId = itemId;
        this.userId = interestedId;
        this.rating = 0.0F;
    }

    public Long getId() {
        return feedbackId;
    }

    public void setId(Long id) {
        this.feedbackId = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getInterestedId() {
        return userId;
    }

    public void setInterestedId(Long interestedId) {
        this.userId = interestedId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
