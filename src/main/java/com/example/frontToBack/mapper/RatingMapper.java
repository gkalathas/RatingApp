package com.example.frontToBack.mapper;

import com.example.frontToBack.model.Rating;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface RatingMapper {

    @Select("SELECT* FROM rating")
    public void getRatings();

    @Insert("INSERT INTO rating (stars) VALUES(#{stars})")
    int insertRating(Rating rating);

}
