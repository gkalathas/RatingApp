package com.example.frontToBack.repository;


import com.example.frontToBack.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    Feedback findByHashKey(String hashKey);
}
