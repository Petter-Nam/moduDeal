package com.application.moduDeal.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.moduDeal.admin.dao.AdminDAO;
import com.application.moduDeal.admin.dto.AdminDTO;
import com.application.moduDeal.admin.dto.RegitDTO;
import com.application.moduDeal.admin.dto.SellerProductDTO;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public AdminDTO login(String username, String password) {
        return adminDAO.login(username, password);
    }

    @Override
    public List<SellerProductDTO> getAllSellerProducts() {
        return adminDAO.getAllSellerProducts();
    }

    @Override
    @Transactional
    public void deleteProduct(int productId) {
        adminDAO.deleteProductLikes(productId);
        adminDAO.deleteProductImages(productId);
        adminDAO.deleteProductFromCart(productId);
        adminDAO.deleteProductReviews(productId);
        adminDAO.deleteProductChats(productId);
        adminDAO.deleteProduct(productId);
    }

	@Override
	public List<RegitDTO> getAllRegitUser() {
		return adminDAO.getAllRegitUser();
	}
}