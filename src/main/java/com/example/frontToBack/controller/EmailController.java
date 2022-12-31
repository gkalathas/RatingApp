package com.example.frontToBack.controller;


import com.example.frontToBack.model.Rating;
import com.example.frontToBack.model.User;
import com.example.frontToBack.service.FeedbackService;
import com.example.frontToBack.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/home")
    public String getRatingForm(Model model, Rating rating) {

        model.addAttribute("rating", new Rating());

        return "home.html";
    }

    @GetMapping("/send")
    public ResponseEntity<String> send() {

        User user = new User();

        mailService.sendEmail(user);
        return new ResponseEntity<>("Email sent", HttpStatus.OK);
    }

    @PostMapping("/saveratingaftersave")
    public String save(Rating rating, Model model) {
//        if(ratingService.saveRating(rating)<1){
//            model.addAttribute("errormessage", String.format("Invalid rating %s", rating.getStars()));
//        }
//        model.addAttribute("successmessage", String.format("Rating %s successfully stored", rating.getStars()));
        float result = rating.getStars();
        System.out.println(result);
        feedbackService.updateFeedback(rating.getStars(), rating.getFeedback().getHashKey());

        model.addAttribute("rating", rating);
        return "saved";
    }


}
