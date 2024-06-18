package com.application.moduDeal.product.service;

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
	public List<ProductDTO> getProductDetails(int productId) {
		return productDAO.getProductDetails(productId);
	}

	@Override
	public List<Map<String,Object>> getRecentProducts() {
		return productDAO.getRecentProducts();
	}

	@Override
	public List<Map<String, Object>> filterProducts(String category) {
	    return productDAO.getFilteredProducts(category);
	}
}