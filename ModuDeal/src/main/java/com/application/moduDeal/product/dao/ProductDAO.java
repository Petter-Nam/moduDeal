package com.application.moduDeal.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;

@Mapper
public interface ProductDAO {

    public void saveProduct(ProductDTO productDTO);
    public void saveProductImages(List<ProductImgDTO> productImgDTOList);
}