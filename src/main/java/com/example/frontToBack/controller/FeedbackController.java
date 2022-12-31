package com.example.frontToBack.controller;


import com.example.frontToBack.model.Feedback;
import com.example.frontToBack.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@Controller
@RequestMapping(value = "/v1/feedbacks")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;
    @PostMapping(value = "/save")
    public ResponseEntity<Feedback> addRating(@RequestBody Feedback feedbackDto) {
        Feedback feedbackToAdd = new Feedback();
        feedbackToAdd.setItemId(feedbackDto.getItemId());
        feedbackToAdd.setRating(feedbackDto.getRating());
        feedbackToAdd.setInterestedId(feedbackDto.getInterestedId());

        System.out.println("posting feedback with info " + feedbackToAdd);
        Feedback feedbackRetrieved = feedbackService.saveFeedback(feedbackToAdd);
        return new ResponseEntity<>(feedbackRetrieved, HttpStatus.CREATED);
    }

    @PostMapping(value = "/submit")
    public ResponseEntity<Feedback> addRating(@RequestBody Feedback feedback, @RequestParam String hash) {
        System.out.println("submiting feedback with info " + feedback.toString());
        float rating = feedback.getRating();
        Feedback feedbackRetrieved = feedbackService.updateFeedback(rating, hash);

        return new ResponseEntity<>(feedbackRetrieved, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() throws ServerException{
        System.out.println("Retrieving feedbacks...");
        List<Feedback> feedbacksRetrieved = feedbackService.getAllFeedbacks();
        if (feedbacksRetrieved == null) {
            throw new ServerException("Error getting customers");
        } else {
            return new ResponseEntity<>(feedbacksRetrieved, HttpStatus.OK);
        }
    }
}
