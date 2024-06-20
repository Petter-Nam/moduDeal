package com.application.moduDeal.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.moduDeal.review.dao.ReviewDAO;
import com.application.moduDeal.review.dto.ReviewDTO;
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public void regitReview(ReviewDTO reviewDTO) {
        reviewDAO.regitReview(reviewDTO);
    }

    @Override
    public List<ReviewDTO> reviewDetail(int productId) {
        return reviewDAO.reviewDetail(productId);
    }
}

