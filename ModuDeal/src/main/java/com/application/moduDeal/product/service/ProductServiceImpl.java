package com.application.moduDeal.product.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.moduDeal.product.dao.ProductDAO;
import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public int saveProduct(ProductDTO productDTO) {
		return productDAO.saveProduct(productDTO);
	}

	@Override
	public void saveProductImages(List<ProductImgDTO> productImgDTOList) {
		productDAO.saveProductImages(productImgDTOList);
	}

	@Override
	public ProductDTO getProductDetails(int productId) {
		return productDAO.getProductDetails(productId);
	}
	
	@Override
    public List<ProductImgDTO> getProductImages(int productId) {
        return productDAO.getProductImages(productId);
    }

	@Override
	public List<Map<String, Object>> getRecentProducts() {
	    List<Map<String, Object>> products = productDAO.getRecentProducts();
	    
	    // 중복 상품을 제거하기 위해 LinkedHashMap 사용
	    Map<Integer, Map<String, Object>> uniqueProducts = new LinkedHashMap<>();

	    for (Map<String, Object> product : products) {
	        int productId = (int) product.get("PRODUCT_ID");
	        if (!uniqueProducts.containsKey(productId)) {
	            uniqueProducts.put(productId, product);
	        }
	    }

	    // Map의 값을 List로 변환하여 반환
	    return new ArrayList<>(uniqueProducts.values());
	}
//
//	@Override
//	public List<Map<String, Object>> filterProducts(String category) {
//	    return productDAO.getFilteredProducts(category);
//	}
}