package com.example.transactionproblem.service;

import com.example.transactionproblem.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("categoryService")
class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findByName(String name) {
        return reviewRepository.findByName(name);
    }

    // this code does not work
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void incrementLikes_variant1(String name) {
        Review review = reviewRepository.findByName(name);
        Long likes = review.getLikes();
        likes = likes + 1;
        review.setLikes(likes);

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // this code does not work either
    @Override
    public void incrementLikes_variant2(String name) {
        Review review = reviewRepository.findByName(name);
        reviewRepository.incrementLikes(review);

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}