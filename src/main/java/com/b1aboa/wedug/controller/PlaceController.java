package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.dto.PlaceSearchLogDTO;
import com.b1aboa.wedug.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api/places")
public class PlaceController {
    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    private final PlaceService searchService;

    @Autowired
    public PlaceController(PlaceService mapService) {
        this.searchService = mapService;
    }

//  /api/places/search?media-code={mediaType}
//  /api/places/search?place={placeCode}
//  /api/places/search?media-type={mediaType}&place={placeCode}
//  /api/places/search?keyword={placeName}
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchPlaceMedia(@RequestParam(value = "media-code", required = false) Long mediaCode,
                                              @RequestParam(value = "keyword", required = false) String keyword,
                                              @RequestParam(value = "place", required = false) Long placeCode) {
        logger.info("Received request with media-code: {}, keyword: {}, place: {}", mediaCode, keyword, placeCode);

        try {
            List<PlaceDTO> searchList;
            if (mediaCode != null && placeCode != null) {
                //media 카테고리, Place 카테고리 둘다 있을 때
                searchList = searchService.searchPlacesByMediaCodeAndPlace(mediaCode, placeCode);
            } else if (mediaCode != null) {
                //Place 카테고리만 있을 때
                searchList = searchService.searchPlacesByMediaCode(mediaCode);
            } else if (placeCode != null) {
                //media 카테고리만 있을 때
                searchList = searchService.searchPlacesByPlaceCode(placeCode);
            } else if (keyword != null && !keyword.trim().isEmpty()) {
                //검색 데이터 있을 때
                System.out.println("keyword ***** " + keyword);
                searchList = searchService.searchPlacesByKeyword(keyword);
                System.out.println("keyword ***** searchList" + searchList);
            } else {
                searchList = searchService.getAllPlaces();
            }

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

    @PostMapping("/search/log")
    public ResponseEntity<String> logPlaceSearch(@RequestBody PlaceSearchLogDTO logDTO) {
        logger.info("Place search log - User ID: {}, Place ID: {}", logDTO.getUserId(), logDTO.getPlaceId());
        return ResponseEntity.ok("Log recorded successfully");
    }

}