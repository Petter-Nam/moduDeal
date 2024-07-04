package com.application.moduDeal.admin.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.moduDeal.admin.dto.AdminDTO;
import com.application.moduDeal.admin.dto.RegitDTO;
import com.application.moduDeal.admin.dto.SellerProductDTO;
import com.application.moduDeal.admin.dto.SellerProductImgDTO;
import com.application.moduDeal.admin.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Value("${file.repo.path}")
    private String fileRepoPath;

    @GetMapping("/login")
    public String loginForm() {
        return "admin/adminLogin";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
    	
    	AdminDTO admin = adminService.login(username, password);
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("memberId", admin.getAdminId());
            session.setAttribute("role", "admin");
            session.setMaxInactiveInterval(60 * 30); // 30분
            return "redirect:/admin/dashBoard";
        }
        return "redirect:/admin/adminLogin?error";
    }

    @GetMapping("/adminMain")
    public String adminMain(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null || !"admin".equals(session.getAttribute("role"))) {
            return "redirect:/admin/login";
        }
        List<SellerProductDTO> sellerProducts = adminService.getAllSellerProducts();
        List<RegitDTO> regitUsers = adminService.getAllRegitUser();
        model.addAttribute("sellerProducts", sellerProducts);
        model.addAttribute("regitUsers", regitUsers);
        return "admin/dashBoard";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam int productId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null || !"admin".equals(session.getAttribute("role"))) {
            return "redirect:/admin/login";
        }
        
        adminService.deleteProduct(productId);
        return "redirect:/admin/dashBoard";
    }

    @GetMapping("/thumbnails")
    @ResponseBody
    public ResponseEntity<Resource> thumbnails(@RequestParam("fileName") String fileName) throws MalformedURLException {
        System.out.println("Requested file: " + fileName);
        Path filePath = Paths.get(fileRepoPath).resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            System.out.println("File not found: " + filePath);
            return ResponseEntity.notFound().build();
        }

        System.out.println("Serving file: " + filePath);
        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_JPEG)
                             .body(resource);
    }
    
    @GetMapping("/noticeBoard")
    public String noticeBoard() {
    	return "admin/noticeBoard";
    }
}