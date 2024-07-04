package com.application.moduDeal.user.controller;




import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.moduDeal.chat.controller.EmailService;
import com.application.moduDeal.user.dto.UserDTO;
import com.application.moduDeal.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/moduDeal")
public class UserController {

	@Value("${file.repo.path}")
	private String fileRepositoryPath;

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/login") // 로그인 화면을 부르는 코드입니다.
	public String login() {
		return "moduDeal/login";
	}

	@PostMapping("/login")
	@ResponseBody
	public Map<String, String> login(@RequestBody UserDTO userDTO, HttpServletRequest request, 
	                                 @RequestParam(value = "redirect", required = false) String redirect) {
	    Map<String, String> response = new HashMap<>();
	    UserDTO resultDTO = userService.login(userDTO);

	    if (resultDTO != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("userId", userDTO.getUserId());
	        response.put("status", "success");
	        
	        if (redirect != null && !redirect.isEmpty()) {
	            response.put("redirect", redirect);
	        }
	    } else {
	        response.put("status", "fail");
	    }

	    return response;
	}

	@GetMapping("/logOut") // 로그아웃하는 코드입니다.
	@ResponseBody
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		String jsScript = """
				<script>
					alert('로그아웃 되었습니다.');
					location.href = '/';
				</script>""";
		return jsScript;
	}

	@GetMapping("/register") // 회원가입 화면을 부르는 코드입니다.
	public String regit() {
		return "moduDeal/register";
	}

	@PostMapping("/register") // 회원가입 로직을 처리하는 코드입니다.
	public String regit(@ModelAttribute UserDTO userDTO) {
		userService.createUser(userDTO);
		return "redirect:/";
	}

	@PostMapping("/validId") // 회원가입시 ID 중복 확인하는 코드입니다.
	@ResponseBody
	public String validId(@RequestParam("userId") String userId) {
		return userService.checkValidId(userId);
	}

	@GetMapping("/myPage") // 마이페이지 화면 불러오는 코드입니다.
	public String myPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		// 로그인한 회원의 정보를 가져옵니다.
		UserDTO userDTO = userService.getUserDetail(userId);

		// 모델에 회원 정보를 추가합니다.
		model.addAttribute("userDTO", userDTO);

		return "moduDeal/myPage";
	}

	@PostMapping("/changePassword")
	@ResponseBody
	public String changePassword(@RequestParam("userId") String userId,
			@RequestParam("password") String newPassword, HttpServletRequest request) {

		// 비밀번호 변경 로직을 수행합니다.
		boolean passwordChanged = userService.changePassword(userId, newPassword);

		if (passwordChanged) {
			HttpSession session = request.getSession();
			session.invalidate();

			String jsScript = """
					<script>
						alert('로그아웃 되었습니다.');
						location.href = '/';
					</script>""";

			return jsScript;
		} else {
			return "Failed to change password.";
		}
	}

	@PostMapping("/updateHp")
	@ResponseBody
	public String updateHp(@ModelAttribute UserDTO userDTO) {
		
		// 폰번호 수정 로직
		boolean hpUpdated = userService.updateHp(userDTO);

		if (hpUpdated) {
			 String jsScript = """
	                    <script>
	                        alert('휴대전화번호가 수정되었습니다.');
	                        location.href = '/moduDeal/myPage';
	                    </script>""";
	            return jsScript;
		} else {
			 String jsScript = """
	                    <script>
	                        alert('휴대전화번호가 수정에 실패했습니다.');
	                        location.href = '/moduDeal/myPage';
	                    </script>""";
	            return jsScript;
		}
	}
	

	    

	    @PostMapping("/updateEmail")
	    @ResponseBody
	    public String updateEmail(@ModelAttribute UserDTO userDTO) {
	        // 이메일을 업데이트합니다.
	        boolean emailUpdated = userService.updateEmail(userDTO);
	        
	        if (emailUpdated) {
	            String jsScript = """
	                    <script>
	                        alert('이메일이 수정되었습니다.');
	                        location.href = '/moduDeal/myPage';
	                    </script>""";
	            return jsScript;
	        } else {
	            String jsScript = """
	                    <script>
	                        alert('이메일 수정에 실패했습니다.');
	                        location.href = '/moduDeal/myPage';
	                    </script>""";
	            return jsScript;
	        }
	    }

	@PostMapping("/updateAddress")
	@ResponseBody
	public String updateAddress(@ModelAttribute UserDTO userDTO) {
		// 주소를 업데이트합니다.
		boolean addressUpdated = userService.updateAddress(userDTO);

		if (addressUpdated) {
			String jsScript = """
					<script>
						alert('주소설정이 변경되었습니다.');
						location.href = '/moduDeal/myPage';
					</script>""";

			return jsScript;
		} else {
			String jsScript = """
					<script>
						alert('주소설정이 올바르지 못한 형식입니다.
						다시 설정하여주세요.');
						location.href = '/moduDeal/myPage';
					</script>""";

			return jsScript;
		}
	}
	

	@GetMapping("/findId")
	public String findId() {
	    return "moduDeal/findId";
	}

	@GetMapping("/findPassword")
	public String findPassword() {
	    return "moduDeal/findPassword";
	}

	@PostMapping("/sendVerificationForId")
	@ResponseBody
	public String sendVerificationForId(@RequestParam("name") String name, @RequestParam("hp") String hp) {
		if (name == null || name.isEmpty() || hp == null || hp.isEmpty()) {
	        return "bad_request";
	    }
	    UserDTO userDTO = new UserDTO();
	    userDTO.setName(name);
	    userDTO.setHp(hp);
	    String userId = userService.findIdByNameAndPhone(userDTO);
	    if (userId != null) {
	        String email = userService.findEmailByNameAndPhone(userDTO); 
	        if (email != null && !email.isEmpty()) {
	            String verificationCode = generateVerificationCode();
	            // Save verification code to session or database
	            emailService.sendVerificationCode(email, verificationCode);
	            return "success";
	        } else {
	            return "email_not_found";
	        }
	    }
	    return "user_not_found";
	}

	@PostMapping("/verifyCodeAndGetId")
	@ResponseBody
	public Map<String, Object> verifyCodeAndGetId(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("verificationCode") String verificationCode) {
	    Map<String, Object> response = new HashMap<>();
	    UserDTO userDTO = new UserDTO();
	    userDTO.setName(name);
	    userDTO.setHp(hp);
	    // Verify the code (compare with saved code)
	    if (isVerificationCodeValid(verificationCode)) {
	        String userId = userService.findIdByNameAndPhone(userDTO);
	        response.put("success", true);
	        response.put("userId", userId);
	    } else {
	        response.put("success", false);
	    }
	    return response;
	}

	@PostMapping("/sendVerificationForPassword")
	@ResponseBody
	public String sendVerificationForPassword(@RequestParam("name") String name, @RequestParam("userId") String userId, @RequestParam("hp") String hp) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(name);
		userDTO.setUserId(userId);
		userDTO.setHp(hp);

		if (userService.checkUserExists(userDTO)) {
			String email = userService.findEmailByNameAndPhone(userDTO);
			if (email == null || email.isEmpty()) {
				return "email_not_found";
			}
			String verificationCode = generateVerificationCode();
			emailService.sendVerificationCode(email, verificationCode);
			return "success";
		} else {
			return "user_not_found";
		}
	}
	@PostMapping("/verifyCodeForPassword")
	@ResponseBody
	public String verifyCodeForPassword(@RequestParam("userId") String userId, @RequestParam("verificationCode") String verificationCode) {
		if (isVerificationCodeValid(verificationCode)) {
			return "success";
		} else {
			return "invalid_code";
		}
	}

	@PostMapping("/resetPassword")
	@ResponseBody
	public String resetPassword(@RequestParam("userId") String userId, @RequestParam("password") String password) {
		if (userService.resetPassword(userId, password)) {
			return "success";
		} else {
			return "failure";
		}
	}

	private String generateVerificationCode() {
	    // Generate a random 6-digit code
	    return String.format("%06d", new Random().nextInt(999999));
	}

	private boolean isVerificationCodeValid(String code) {
	    // Implement verification logic (compare with saved code)
	    return true; // Placeholder
	}
}
