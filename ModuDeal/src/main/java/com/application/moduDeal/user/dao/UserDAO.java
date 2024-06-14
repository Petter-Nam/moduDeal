package com.application.moduDeal.user.dao;



import org.apache.ibatis.annotations.Mapper;

import com.application.moduDeal.user.dto.UserDTO;

@Mapper
public interface UserDAO {
	
	public void createUser(UserDTO userDTO);
	public String checkValidId(String userId);
	public UserDTO login(String userId);
	public UserDTO getUserDetail(String userId);
	public void changePassword(UserDTO userDTO);
	public void updateEmail(UserDTO userDTO);
	public void updateHp(UserDTO userDTO);
	public void updateAddress(UserDTO userDTO);
	
}
