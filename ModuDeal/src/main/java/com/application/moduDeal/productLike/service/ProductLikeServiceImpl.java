package com.application.moduDeal.productLike.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.moduDeal.productLike.dao.ProductLikeDAO;
import com.application.moduDeal.productLike.dto.ProductLikeDTO;

@Service
public class ProductLikeServiceImpl implements ProductLikeService {

    @Autowired
    private ProductLikeDAO productLikeDAO;

    @Override
    public void toggleLike(ProductLikeDTO productLikeDTO) {
        boolean isLike = productLikeDAO.isLike(productLikeDTO);

        if (isLike) {
            productLikeDAO.decrementLikeCount(productLikeDTO);
        } else {
            productLikeDAO.incrementLikeCount(productLikeDTO);
        }
    }

    @Override
    public boolean isLike(ProductLikeDTO productLikeDTO) {
        return productLikeDAO.isLike(productLikeDTO);
    }

    @Override
    public int getLikeCount(int productId) {
        return productLikeDAO.getLikeCount(productId);
    }

}