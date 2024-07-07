package com.application.moduDeal.user.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.moduDeal.admin.dto.NoticeDTO;
import com.application.moduDeal.user.dao.UserDAO;
import com.application.moduDeal.user.dto.UserDTO;



@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	
	@Override
	public void createUser(UserDTO userDTO) {
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userDAO.createUser(userDTO);
	}

	@Override
	public String checkValidId(String userId) {
		String isValidId = "n";
		if (userDAO.checkValidId(userId) == null) {
			isValidId = "y";
		}
		return isValidId;
	}

	@Override
	public UserDTO login(UserDTO userDTO) {
		UserDTO resultDTO = userDAO.login(userDTO.getUserId());
		
		if (resultDTO != null) {
			if (passwordEncoder.matches(userDTO.getPassword(), resultDTO.getPassword())) {
				return resultDTO;
			};
		}
		return null;
	}

	@Override
	public UserDTO getUserDetail(String userId) {
		return userDAO.getUserDetail(userId);
	}

	public boolean changePassword(String userId, String newPassword) {
		
		// userId를 사용하여 사용자 정보를 조회합니다.
	    UserDTO userDTO = userDAO.getUserDetail(userId);
	    
	    if (userDTO != null) {
	        // 새로운 비밀번호를 암호화합니다.
	        String encryptedNewPassword = passwordEncoder.encode(newPassword);
	        
	        // 암호화된 새로운 비밀번호를 사용자 정보에 설정합니다.
	        userDTO.setPassword(encryptedNewPassword);
	        
	        // 변경된 비밀번호로 사용자 정보를 업데이트합니다.
	        userDAO.changePassword(userDTO);
	        
	        return true; // 비밀번호 변경 성공
	    } else {
	        return false; // 사용자 정보를 찾을 수 없는 경우
	    }
	    
	}

	 @Override
	    public boolean updateEmail(UserDTO userDTO) {
	       
		 if (userDTO.getEmail() != null) {
			 userDAO.updateEmail(userDTO);
			 return true;
		 }
		 else {
			 return false;
		 }
		 
	   }
	

	@Override
    public boolean updateAddress(UserDTO userDTO) {
		 if (userDTO.getAddressZipcode() != null && userDTO.getAddressRoad() != null &&
		            userDTO.getAddressJibun() != null && userDTO.getAddressNamuji() != null) {
			 userDAO.updateAddress(userDTO);
			 return true;
		 }
		 else {
			 return false;
		 }
    }

	@Override
	public boolean updateHp(UserDTO userDTO) {
		if (userDTO.getHp() != null) {
			userDAO.updateHp(userDTO);
			return true;
		}
		else {
			return false;
		}
	}	
	
	@Override
	public String findIdByNameAndPhone(UserDTO userDTO) {
	    return userDAO.findIdByNameAndPhone(userDTO);
	}

	@Override
	public boolean checkUserExists(UserDTO userDTO) {
		UserDTO userFromDb = userDAO.checkUserExists(userDTO);
		return userFromDb != null;
	}

	@Override
	public boolean resetPassword(String userId, String password) {
	    String encodedPassword = passwordEncoder.encode(password);
	    return userDAO.updatePassword(userId, encodedPassword) > 0;
	}
	
	@Override
	public String findEmailByNameAndPhone(UserDTO userDTO) {
		String email = userDAO.findEmailByNameAndPhone(userDTO);
		if (email == null || email.isEmpty()) {
			logger.error("Email address is null or empty for user: {}", userDTO);
		}
		return email;
	}
	
	@Override
    public List<NoticeDTO> getAllNotice() {
        return userDAO.getAllNotice();
    }
}
