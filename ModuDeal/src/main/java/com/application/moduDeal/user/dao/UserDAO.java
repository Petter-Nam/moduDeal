package com.application.moduDeal.user.dao;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	public String findIdByEmail(UserDTO userDTO);
	public String findIdByNameAndPhone(UserDTO userDTO);
	public UserDTO checkUserExists(UserDTO userDTO);
	public int updatePassword(@Param("userId") String userId, @Param("password") String password);
	public String findEmailByNameAndPhone(UserDTO userDTO);
	
}
