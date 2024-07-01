package com.application.moduDeal.productLike.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.moduDeal.productLike.dto.ProductLikeDTO;
import com.application.moduDeal.productLike.service.ProductLikeService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/like")
public class ProductLikeController {

    @Autowired
    private ProductLikeService productLikeService;

    @GetMapping("/toggleCheck")
    public String toggleCheck(@RequestParam("productId") int productId, @RequestParam("userId") String userId,
                              HttpSession session, Model model) {

        if (userId == null || userId.isEmpty()) {
            return "redirect:/moduDeal/login";
        }

        ProductLikeDTO productLikeDTO = new ProductLikeDTO();
        productLikeDTO.setUserId(userId);
        productLikeDTO.setProductId(productId);

        productLikeService.toggleLike(productLikeDTO);
        
        return "redirect:/";
    }

}