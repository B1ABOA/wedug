package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public List<PlaceDTO> searchPlaceMedia(@RequestParam("media-type") String mediaType,
                               @RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "place", required = false) String place) {
        List<PlaceDTO> searchList = searchService.searchPlacesByMediaType(mediaType);


        return searchList;
    }

}