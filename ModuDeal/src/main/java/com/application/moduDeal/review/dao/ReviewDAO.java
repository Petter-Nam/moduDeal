package com.application.moduDeal.review.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.moduDeal.admin.dto.NoticeDTO;
import com.application.moduDeal.review.dto.NoticeReviewDTO;
import com.application.moduDeal.review.dto.ReviewDTO;

@Mapper
public interface ReviewDAO {

	public void regitReview(ReviewDTO reviewDTO);
	public List<ReviewDTO> reviewDetail(int productId);
	public NoticeDTO getNoticeById(int noticeId);
	public List<NoticeReviewDTO> getNoticeReviews(int noticeId);
	public void regitNoticeReview(NoticeReviewDTO reviewDTO);
	
}
