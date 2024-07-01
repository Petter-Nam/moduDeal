package com.application.moduDeal.productLike.dao;


import org.apache.ibatis.annotations.Mapper;

import com.application.moduDeal.productLike.dto.ProductLikeDTO;

@Mapper
public interface ProductLikeDAO {
    public boolean isLike(ProductLikeDTO productLikeDTO);
    public void incrementLikeCount(ProductLikeDTO productLikeDTO);
    public void decrementLikeCount(ProductLikeDTO productLikeDTO);
    public int getLikeCount(int productId);
}