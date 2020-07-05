package com.example.transactionproblem.service;

import com.example.transactionproblem.domain.Review;

import java.util.List;

public interface ReviewService {

    Review save(Review review);

    List<Review> findAll();

    Review findByName(String name);

    void incrementLikes_variant1(String name);
    void incrementLikes_variant2(String name);
}
