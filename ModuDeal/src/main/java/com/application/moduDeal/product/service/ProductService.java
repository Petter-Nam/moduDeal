package com.application.moduDeal.product.service;


import java.util.List;
import java.util.Map;

import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;

public interface ProductService {
	public int saveProduct(ProductDTO productDTO);
	
    public void saveProductImages(List<ProductImgDTO> productImgDTOList);
    
    public List<ProductImgDTO> getProductImages(int productId); 
    
    public ProductDTO getProductDetails(int productId);
    
    public List<Map<String,Object>> getRecentProducts(); // 최근 상품 목록을 가져오는 메서드 추가
   // public List<Map<String, Object>> filterProducts(String category);
}