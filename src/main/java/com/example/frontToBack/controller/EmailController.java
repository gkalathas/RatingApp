package com.example.frontToBack.controller;


import com.example.frontToBack.model.Feedback;
import com.example.frontToBack.model.Rating;
import com.example.frontToBack.model.User;
import com.example.frontToBack.service.FeedbackService;
import com.example.frontToBack.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
public class EmailController {

private final FeedbackService feedbackService;


private final MailService mailService;

    @Autowired
    public EmailController(FeedbackService feedbackService,
                           MailService mailService) {
        this.feedbackService = feedbackService;
        this.mailService = mailService;
    }


    @RequestMapping("/{url}")
    public String showRatingForm(Model model, @PathVariable String url) {
        model.addAttribute("rating", new Rating());
        model.addAttribute("hash", url);
        return "ratingForm";
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody User user) throws MessagingException, UnsupportedEncodingException {

        //saving feedback except rating during email sending
        Feedback feedback = new Feedback();
        feedback.setInterestedId(user.getId());
        feedback.setItemId(user.getItemId());
        feedback.setRating(0F);
        feedback.setHashKey("http://localhost:8080"+ user.hashCode());
        feedbackService.saveFeedback(feedback);


        mailService.sendEmail(user);
        return new ResponseEntity<>("Email sent", HttpStatus.OK);
    }

    @PostMapping("/saveratingaftersave")
    public String save(Rating rating, Model model) {
        float result = rating.getStars();
        String hash = (String) model.getAttribute("hash");
        System.out.println(hash + "hash submitted");
//        if(result <= 0){
//            model.addAttribute("errormessage", String.format("Invalid rating %s", rating.getStars()));
//        }
//        model.addAttribute("successmessage", String.format("Rating %s successfully stored", rating.getStars()));

        System.out.println(result);
//        feedbackService.updateFeedback(rating.getStars(), rating.getFeedback().getHashKey());

        model.addAttribute("rating", rating);
        return "saved";
    }


}
