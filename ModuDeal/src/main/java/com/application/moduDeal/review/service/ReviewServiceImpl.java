package com.application.moduDeal.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.moduDeal.admin.dto.NoticeDTO;
import com.application.moduDeal.review.dao.ReviewDAO;
import com.application.moduDeal.review.dto.NoticeReviewDTO;
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

	@Override
	public NoticeDTO getNoticeById(int noticeId) {
		return reviewDAO.getNoticeById(noticeId);
	}
	
	
	@Override
    public List<NoticeReviewDTO> getNoticeReviews(int noticeId) {
        return reviewDAO.getNoticeReviews(noticeId);
    }
	
	   @Override
	    public void regitNoticeReview(NoticeReviewDTO reviewDTO) {
	        reviewDAO.regitNoticeReview(reviewDTO);
	    }
}