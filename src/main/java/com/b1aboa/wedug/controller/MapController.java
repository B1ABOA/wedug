package com.b1aboa.wedug.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey;

    @GetMapping("/map")
    public String showMap(Model model){
        model.addAttribute("apiKey",kakaoMapsApiKey);
        return "map";
    }
}
