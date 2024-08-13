package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService mapService) {
        this.searchService = mapService;
    }

    @GetMapping("/search")
    public String showMap(Model model) {
        model.addAttribute("apiKey", searchService.getKakaoMapsApiKey());
        return "search/map-search";
    }
}