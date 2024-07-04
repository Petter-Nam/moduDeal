package com.application.moduDeal.admin.service;

import java.util.List;

import com.application.moduDeal.admin.dto.AdminDTO;
import com.application.moduDeal.admin.dto.RegitDTO;
import com.application.moduDeal.admin.dto.SellerProductDTO;

public interface AdminService {

	public AdminDTO login(String username, String password);
    public List<SellerProductDTO> getAllSellerProducts();
    public void deleteProduct(int productId);
    public List<RegitDTO> getAllRegitUser();
	
}
