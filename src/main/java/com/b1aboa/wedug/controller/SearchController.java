package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.entity.Place;
import com.b1aboa.wedug.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

//     /api/places/search?media-type={mediaType}
    @GetMapping("/api/places/search")
    public String searchPlaceMedia(@RequestParam("media-type") String mediaType, Model model) {
        List<PlaceDTO> searchList = searchService.searchPlacesByMediaType(mediaType);
        model.addAttribute("searchList", searchList);
        return "search/map-search";
    }

}