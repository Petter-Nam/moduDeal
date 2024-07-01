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
    
    public ProductDTO getProductDetails(int productId);
    
    public List<ProductImgDTO> getProductImages(@Param("productId") int productId);
    
    public List<Map<String, Object>> getRecentProducts();
    
    public List<ProductDTO> getProductsByUserId(String userId);
    
    public void updateProduct(ProductDTO product);

    public void deleteProductImageById(int imageId);
    
    public void deleteProductById(int productId);
    
    public List<Map<String, Object>> getTopLikedProducts();

}