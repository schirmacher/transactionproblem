package com.example.transactionproblem.web;

import com.example.transactionproblem.domain.Review;
import com.example.transactionproblem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LikeController {

	@Autowired
	private ReviewService categoryService;

	@RequestMapping("/")
	@ResponseBody
	public String increaseLike() {
		categoryService.incrementLikes_variant2("A random movie");
		Review review = categoryService.findByName("A random movie");

		return String.format("Movie '%s' has %d likes", review.getName(), review.getLikes());
	}
}
