package com.application.moduDeal.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.moduDeal.cart.dto.CartDTO;
import com.application.moduDeal.cart.service.CartService;
import com.application.moduDeal.product.dto.ProductDTO;
import com.application.moduDeal.product.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cartList")
    public String viewCart(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/moduDeal/login";
        }

        List<CartDTO> cartItems = cartService.getCartItemsByUserId(userId);
        model.addAttribute("cartItems", cartItems);

        List<ProductDTO> myProducts = productService.getProductsByUserId(userId);
        model.addAttribute("myProducts", myProducts);
        
        return "moduDeal/cart";
    }

    @PostMapping("/addToCart")
    public String addToCart(@ModelAttribute CartDTO cartDTO, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/moduDeal/login";
        }

        // 세션의 사용자 아이디를 사용하여 설정
        cartDTO.setUserId(userId);

        cartService.addToCart(cartDTO);

        return "redirect:/cart/cartList";
    }

    @PostMapping("/deleteCartItem")
    public String deleteCartItem(@RequestParam("cartId") int cartId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/moduDeal/login";
        }

        // 장바구니 항목이 해당 사용자의 것인지 확인
        CartDTO cartItem = cartService.getCartItemById(cartId);
        if (cartItem == null || !cartItem.getUserId().equals(userId)) {
            return "redirect:/cart/cartList";
        }

        cartService.deleteCartItem(cartId);

        return "redirect:/cart/cartList";
    }
}
