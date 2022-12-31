package com.example.frontToBack.controller;

import com.example.frontToBack.model.Rating;
import com.example.frontToBack.service.RatingService;
import org.springframework.ui.Model;

//@Controller
public class RatingController {

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    //@GetMapping("/home")
    public String showTheRatingForm(Model model, Rating rating) {

        model.addAttribute("rating", new Rating());

        return "home";
    }

    //@PostMapping("/save")
    public String save(Rating rating, Model model) {
        if(ratingService.saveRating(rating)<1){
          model.addAttribute("errormessage", String.format("Invalid rating %s", rating.getStars()));
        }
        model.addAttribute("successmessage", String.format("Rating %s successfully stored", rating.getStars()));

        float result = rating.getStars();
        System.out.println(result);

        model.addAttribute("rating", rating);
        return "saved";
    }


}
