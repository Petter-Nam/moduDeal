package com.application.moduDeal.review.service;

import java.util.List;

import com.application.moduDeal.review.dto.ReviewDTO;

public interface ReviewService {

	public void regitReview(ReviewDTO reviewDTO);
	public List<ReviewDTO> reviewDetail(int productId);
	
}
