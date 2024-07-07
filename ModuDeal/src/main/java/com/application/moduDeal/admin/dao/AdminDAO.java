package com.application.moduDeal.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.application.moduDeal.admin.dto.AdminDTO;
import com.application.moduDeal.admin.dto.NoticeDTO;
import com.application.moduDeal.admin.dto.RegitDTO;
import com.application.moduDeal.admin.dto.SellerProductDTO;

@Mapper
public interface AdminDAO {
	public AdminDTO login(@Param("adminId") String adminId, @Param("password") String password);
    public List<SellerProductDTO> getAllSellerProducts();
    public void deleteProductLikes(int productId);
    public void deleteProductImages(int productId);
    public void deleteProductFromCart(int productId);
    public void deleteProductReviews(int productId);
    public void deleteProductChats(int productId);
    public void deleteProduct(int productId);
    public List<RegitDTO> getAllRegitUser();
    public void saveNotice(NoticeDTO notice);
    public List<NoticeDTO> getAllNotices();
    public NoticeDTO getNoticeById(int noticeId);
    public void updateNotice(NoticeDTO notice);
    public void deleteNotice(int noticeId);
}