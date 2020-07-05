package com.example.transactionproblem.service;

import com.example.transactionproblem.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(Review review);

    List<Review> findAll();

    Review findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Review c SET c.likes = c.likes + 1 WHERE c = :review")
    void incrementLikes(Review review);
}
