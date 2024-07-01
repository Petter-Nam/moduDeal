package com.application.moduDeal;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.application.moduDeal.product.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ModuDealMainController {

	@Autowired
    private ProductService productService;
	
	@GetMapping
    public String getCategoryPage(HttpSession session, Model model) {

        List<Map<String, Object>> topLikedProducts = productService.getTopLikedProducts();
        model.addAttribute("topLikedProducts", topLikedProducts);
        
        return "moduDeal/main";
    }
}