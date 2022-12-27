package com.example.frontToBack.service;

import com.example.frontToBack.mapper.RatingMapper;
import com.example.frontToBack.model.Rating;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private RatingMapper ratingMapper;

    public RatingService(RatingMapper ratingMapper) {
        this.ratingMapper = ratingMapper;
    }

    public int saveRating(Rating rating) {
        return ratingMapper.insertRating(rating);
    }
}
