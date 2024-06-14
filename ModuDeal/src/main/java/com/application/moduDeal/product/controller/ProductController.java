package com.application.moduDeal.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;
import com.application.moduDeal.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class ProductController {

	@Value("${file.repo.path}")
	private String fileRepoPath;

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public String product(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("userId");
		
		if (loginUser == null) {
			return "redirect:/moduDeal/login";
		}
		
		return "moduDeal/product";
	}
	
	@PostMapping("/product") // 상품정보 , 이미지 저장 게시판
	@ResponseBody
	public String product(@RequestParam("imgOriginalName") List<MultipartFile> uploadProfile,
			@ModelAttribute ProductDTO productDTO, HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("userId");
		
		productDTO.setUserId(loginUser);
		//ProductDTO 날짜 생성 및 데이터 저장
		productDTO.setProductAt(new Date());
		
		productService.saveProduct(productDTO);

	
		List<ProductImgDTO> productImgDTOList = new ArrayList<>();
		for (MultipartFile file : uploadProfile) {
			if (!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				String uploadProfileName = uuid + extension;
				
				
				file.transferTo(new File(fileRepoPath + uploadProfileName));
				ProductImgDTO productImgDTO = new ProductImgDTO();
				
				
				productImgDTO.setImgUuid(uploadProfileName);
				productImgDTO.setProductId(productDTO.getProductId());
				productImgDTO.setImgOriginalName(originalFileName);
				productImgDTO.setImgAt(new Date());
				productImgDTOList.add(productImgDTO);
			}
		}
		productService.saveProductImages(productImgDTOList);

		return """
				<script>
				    alert('업로드 되었습니다.');
				    location.href = '/moduDeal/main';
				</script>
				""";
	}

}