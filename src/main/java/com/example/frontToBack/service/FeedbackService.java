package com.example.frontToBack.service;


import com.example.frontToBack.model.Feedback;
import com.example.frontToBack.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(Feedback feedback) {

        Long itemId = feedback.getItemId();
        Long interestedId = feedback.getInterestedId();
        LocalDateTime date = LocalDateTime.now();

        String messageToHash = itemId + " " + interestedId + date;
        String hash = encodeString(messageToHash);
        feedback.setHashKey(hash);

        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(float rating, String hashKey) {
        Feedback feedbackRetrieved = feedbackRepository.findByHashKey(hashKey);
        if (feedbackRetrieved != null) {
            if (feedbackRetrieved.getRating() == 0) {
                feedbackRetrieved.setRating(rating);
                feedbackRepository.save(feedbackRetrieved);

            } else {
                System.out.println("Feedback is already submitted");
            }
        } else {
            System.out.println("Feedback request not found");
        }

        return feedbackRetrieved;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> getFeedbacksById(Long id) {
        return feedbackRepository.findAllById(Collections.singleton(id));
    }

    private String encodeString(String messageToBeHashed) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] encodedBytes = md.digest(messageToBeHashed.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : encodedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
