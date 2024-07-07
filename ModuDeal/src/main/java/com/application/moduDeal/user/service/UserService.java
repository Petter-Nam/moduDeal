package com.application.moduDeal.user.service;

import java.util.List;

import com.application.moduDeal.admin.dto.NoticeDTO;
import com.application.moduDeal.user.dto.UserDTO;

public interface UserService {
	
	public void createUser(UserDTO userDTO);
	public String checkValidId(String userId);
	public UserDTO login(UserDTO userDTO);
	public UserDTO getUserDetail(String userId);
	public boolean changePassword(String userId, String newPassword);
	public boolean updateHp(UserDTO userDTO);
	public boolean updateEmail(UserDTO userDTO);
	public boolean updateAddress(UserDTO userDTO);
	public String findIdByNameAndPhone(UserDTO userDTO);
	public boolean checkUserExists(UserDTO userDTO);
	public boolean resetPassword(String userId, String password);
	public String findEmailByNameAndPhone(UserDTO userDTO);
	public List<NoticeDTO> getAllNotice();
	
}
