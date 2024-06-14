package com.application.moduDeal.product.service;


import java.util.List;

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
	public void saveProduct(ProductDTO productDTO) {
		productDAO.saveProduct(productDTO);
	}
	
	
	@Override
    public void saveProductImages(List<ProductImgDTO> productImgDTOList) {
        productDAO.saveProductImages(productImgDTOList);
    }
    
}