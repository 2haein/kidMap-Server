package com.codeboogie.kidmapbackend.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/kakaoaddr")
    public String addressPage(){
        return "kakaoAddr.html";
    }
}
