package com.application.moduDeal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModuDealMainController {

    @GetMapping()
    public String main() {
        return "moduDeal/main.html";
    }
}