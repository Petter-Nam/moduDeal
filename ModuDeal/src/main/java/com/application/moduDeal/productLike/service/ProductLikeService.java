package com.application.moduDeal.productLike.service;

import com.application.moduDeal.productLike.dto.ProductLikeDTO;



public interface ProductLikeService {
    public void toggleLike(ProductLikeDTO productLikeDTO);
    public boolean isLike(ProductLikeDTO productLikeDTO);
    public int getLikeCount(int productId);
}
