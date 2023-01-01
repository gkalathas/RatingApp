package com.example.frontToBack.mapper;

import com.example.frontToBack.model.Rating;

//@Mapper
public interface RatingMapper {

    //@Select("SELECT* FROM rating")
    public void getRatings();

    //@Insert("INSERT INTO rating (stars) VALUES(#{stars})")
    int insertRating(Rating rating);

}
