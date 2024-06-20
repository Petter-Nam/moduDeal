package com.application.moduDeal.review.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.moduDeal.review.dto.ReviewDTO;

@Mapper
public interface ReviewDAO {

	public void regitReview(ReviewDTO reviewDTO);
	public List<ReviewDTO> reviewDetail(int productId);
	
}
