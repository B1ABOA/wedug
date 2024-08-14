package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

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

//     /api/places/search?media-code={mediaType}
@GetMapping("/api/places/search")
@ResponseBody
public ResponseEntity<?> searchPlaceMedia(@RequestParam("media-code") long mediaCode,
                                          @RequestParam(value = "keyword", required = false) String keyword,
                                          @RequestParam(value = "place", required = false) String place) {
    logger.info("Received request with media-code: {}, keyword: {}, place: {}", mediaCode, keyword, place);

    try {
        List<PlaceDTO> searchList = searchService.searchPlacesByMediaCode(mediaCode);
        logger.info("Search result size: {}", searchList != null ? searchList.size() : 0);

        if (searchList == null || searchList.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        return ResponseEntity.ok(searchList);
    } catch (Exception e) {
        logger.error("An error occurred during search", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }
}


}