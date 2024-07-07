package com.application.moduDeal.admin.service;

import java.util.List;

import com.application.moduDeal.admin.dto.AdminDTO;
import com.application.moduDeal.admin.dto.NoticeDTO;
import com.application.moduDeal.admin.dto.RegitDTO;
import com.application.moduDeal.admin.dto.SellerProductDTO;

public interface AdminService {

	public AdminDTO login(String adminId, String password);
    public List<SellerProductDTO> getAllSellerProducts();
    public void deleteProduct(int productId);
    public List<RegitDTO> getAllRegitUser();
    public void saveNotice(NoticeDTO notice);
    public List<NoticeDTO> getAllNotices();
    public NoticeDTO getNoticeById(int noticeId);
    public void updateNotice(NoticeDTO notice);
    public void deleteNotice(int noticeId);
	
}
