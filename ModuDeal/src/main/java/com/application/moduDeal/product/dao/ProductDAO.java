package com.application.moduDeal.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;
@Mapper
public interface ProductDAO {
	public int saveProduct(ProductDTO productDTO);
    public void saveProductImages(List<ProductImgDTO> productImgDTOList);
    public List<ProductDTO> getProductDetails(@Param("productId") int productId);
    public List<Map<String, Object>> getRecentProducts();
    public List<Map<String, Object>> getFilteredProducts(@Param("category") String category);

}