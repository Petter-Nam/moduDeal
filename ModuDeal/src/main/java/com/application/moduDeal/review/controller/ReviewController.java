package com.application.moduDeal.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.moduDeal.admin.dto.NoticeDTO;
import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.dto.ProductImgDTO;
import com.application.moduDeal.product.service.ProductService;
import com.application.moduDeal.review.dto.NoticeReviewDTO;
import com.application.moduDeal.review.dto.ReviewDTO;
import com.application.moduDeal.review.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private ProductService productService;

    @PostMapping("/regitReview")
    public String regitReview(@ModelAttribute ReviewDTO reviewDTO, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/moduDeal/login";
        }

        reviewDTO.setUserId(userId);
        reviewService.regitReview(reviewDTO);


        return "redirect:/review/checkReview?productId=" + reviewDTO.getProductId();
    }

    @GetMapping("/checkReview")
    public String checkReview(Model model, @RequestParam("productId") int productId) {
        List<ReviewDTO> reviewDTO = reviewService.reviewDetail(productId);
        ProductDTO productDTO = productService.getProductDetails(productId);
        List<ProductImgDTO> productImages = productService.getProductImages(productId); 
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("productImages", productImages); 
        model.addAttribute("reviewDTO", reviewDTO);
        return "moduDeal/productInfo";
    }
    
    @PostMapping("/noticeRegitReview")
    public String regitReview(@ModelAttribute("NoticeReviewDTO") NoticeReviewDTO reviewDTO, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/moduDeal/login";
        }
        reviewDTO.setUserId(userId);
        reviewService.regitNoticeReview(reviewDTO);
        return "redirect:/review/notice/" + reviewDTO.getNoticeId();
    }

    @GetMapping("/notice/{noticeId}")
    public String getNoticeDetail(@PathVariable("noticeId") int noticeId, Model model, HttpSession session) {
    	String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/moduDeal/login";
        }
        System.out.println(noticeId + "asdifjiosadfjiosadjfoasidfjoisadjfoiasdjfoisajdfoisadjfoisjfiosjdfioasjdf");
        NoticeDTO notice = reviewService.getNoticeById(noticeId);
        List<NoticeReviewDTO> noticeReviews = reviewService.getNoticeReviews(noticeId);
        model.addAttribute("notice", notice);
        model.addAttribute("noticeReviews", noticeReviews);
        return "moduDeal/noticeReview"; 
    }
}
