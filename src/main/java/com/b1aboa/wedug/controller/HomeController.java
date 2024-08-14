package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    SearchService searchService;

    @Autowired
    public HomeController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("search")
    public String showMap(Model model) {
        model.addAttribute("apiKey", searchService.getKakaoMapsApiKey());
        return "search/map-search";
    }

}