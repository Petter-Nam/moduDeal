package com.application.moduDeal.product.service;


import java.util.List;
import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;

public interface ProductService {
	 public void saveProduct(ProductDTO productDTO);
	 public void saveProductImages(List<ProductImgDTO> productImgDTOList);
}