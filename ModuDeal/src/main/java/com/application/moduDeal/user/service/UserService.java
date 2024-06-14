package com.application.moduDeal.user.service;

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
	
}
