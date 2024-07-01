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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.application.moduDeal.cart.service.CartService;
import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;
import com.application.moduDeal.product.service.ProductService;
import com.application.moduDeal.productLike.service.ProductLikeService;

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
    private CartService cartService;
    
    @Autowired
    private ProductLikeService productLikeService;

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

        // 각 상품에 대한 좋아요 수를 가져와서 recentProducts 리스트의 각 요소에 추가합니다
        for (Map<String, Object> product : recentProducts) {
            int productId = (int) product.get("PRODUCT_ID");
            int likeCount = productLikeService.getLikeCount(productId);
            product.put("likeCount", likeCount); // 각 상품에 좋아요 수 추가
        }

        model.addAttribute("recentProducts", recentProducts);

        return "/moduDeal/category";
    }
    
    @GetMapping("/thumbnails")
    @ResponseBody
    public Resource thumbnails(@RequestParam("fileName") String fileName) throws MalformedURLException {
        // 썸네일 이미지 경로를 생성하여 반환
    
        return new UrlResource("file:" + fileRepoPath + fileName);
    }

    
    @GetMapping("/editProduct")
    public String editProduct(@RequestParam("id") Long productId, Model model) {
        // productId를 사용하여 상품 정보를 DB에서 조회
        ProductDTO product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "/moduDeal/editProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute ProductDTO product,
                                @RequestParam(value = "deleteImageIds", required = false) Long[] deleteImageIds,
                                @RequestParam(value = "newImages", required = false) MultipartFile[] newImages,
                                RedirectAttributes redirectAttributes) throws IOException {
        // 업데이트 로직 작성
        productService.updateProduct(product); // 상품 정보 업데이트

        // 기존 이미지 삭제 처리
        if (deleteImageIds != null) {
            for (Long imageId : deleteImageIds) {
                productService.deleteProductImageById(imageId);
            }
        }

        // 새로운 이미지 업로드 및 DB에 저장
        if (newImages != null && newImages.length > 0) {
            List<ProductImgDTO> newProductImages = new ArrayList<>();
            for (MultipartFile file : newImages) {
                if (!file.isEmpty()) {
                    String originalFileName = file.getOriginalFilename();
                    UUID uuid = UUID.randomUUID();
                    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String uploadProfileName = uuid + extension;

                    file.transferTo(new File(fileRepoPath + uploadProfileName));

                    ProductImgDTO productImgDTO = new ProductImgDTO();
                    productImgDTO.setImgUuid(uploadProfileName);
                    productImgDTO.setProductId(product.getProductId());
                    productImgDTO.setImgOriginalName(originalFileName);
                    productImgDTO.setImgAt(new Date());
                    
                    newProductImages.add(productImgDTO);
                }
            }
            productService.saveProductImages(newProductImages); // 새로운 이미지 저장
        }

        // 수정 완료 후 리다이렉트
        redirectAttributes.addFlashAttribute("message", "상품 수정이 완료되었습니다.");
        return "redirect:/cart/cartList";
    }
    
    
    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int productId) {
    	System.out.println(productId + "auhdfasjdfjasdlfjasidfjasiodjfioasjdfioasjdfioasjdfioasjdfoiasjdfoiajsdiofjasodifjasoidjfoiasdjfoasijfoiasdjfoiasjdfioajdfoisjdf");
        productService.deleteProductById(productId);
        return "redirect:/cart/cartList"; // 삭제 후 상품 목록 페이지로 리다이렉트
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
