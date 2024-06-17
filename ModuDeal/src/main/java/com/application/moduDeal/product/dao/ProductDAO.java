package com.application.moduDeal.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;
@Mapper
public interface ProductDAO {
	public int saveProduct(ProductDTO productDTO);
    public void saveProductImages(List<ProductImgDTO> productImgDTOList);
    public List<ProductDTO> getProductDetails(@Param("productId") int productId);
    public List<ProductDTO> getRecentProducts(); // 최근 상품 목록을 가져오는 메서드 추가
//    public List<ProductDTO> getRecentProductsFiltered(@Param("minPrice") Integer minPrice,
//                                                      @Param("maxPrice") Integer maxPrice,
//                                                      @Param("category") String category);

}