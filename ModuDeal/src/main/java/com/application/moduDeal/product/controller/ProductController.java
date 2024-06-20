package com.application.moduDeal.product.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.application.moduDeal.review.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class ProductController {

    @Value("${file.repo.path}")
    private String fileRepoPath;

    @Autowired
    private ProductService productService;
    
    
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/product")
    public String product(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String loginUser = (String) session.getAttribute("userId");

        if (loginUser == null) {
            return "redirect:/moduDeal/login";
        }

        return "moduDeal/product";
    }

    @PostMapping("/product")
    @ResponseBody
    public String product(@RequestParam("imgOriginalName") List<MultipartFile> uploadProfile,
                          @ModelAttribute ProductDTO productDTO, HttpServletRequest request) throws IllegalStateException, IOException {
        HttpSession session = request.getSession();
        String loginUser = (String) session.getAttribute("userId");

        productDTO.setUserId(loginUser);
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
                    alert('상품이 등록되었습니다..');
                    location.href = '/';
                </script>
                """;
    }

    @GetMapping("/category")
    public String getCategoryPage(Model model) {
        List<Map<String,Object>> recentProducts = productService.getRecentProducts();

        model.addAttribute("recentProducts", recentProducts);

        return "/moduDeal/category";
    }

    
    @GetMapping("/thumbnails")
    @ResponseBody
    public Resource thumbnails(@RequestParam("fileName") String fileName) throws MalformedURLException {
        // 썸네일 이미지 경로를 생성하여 반환
    
        return new UrlResource("file:" + fileRepoPath + fileName);
    }

//    @GetMapping("/filterProducts")
//    public String filterProducts(@RequestParam(required = false) String category,
//                                 Model model) {
//        List<Map<String, Object>> filteredProducts = productService.filterProducts(category);
//        model.addAttribute("recentProducts", filteredProducts);
//        return "/moduDeal/category";
//    }
//    


}
