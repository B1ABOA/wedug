package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.dto.PlaceSearchLogDTO;
import com.b1aboa.wedug.dto.UserDTO;
import com.b1aboa.wedug.service.PlaceService;
import com.b1aboa.wedug.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api/places")
public class PlaceController {
    @Autowired
    private UserService userService;
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
                                              @RequestParam(value = "place", required = false) Long placeCode,
                                              Authentication authentication) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        logger.info("Received request with media-code: {}, keyword: {}, place: {}", mediaCode, keyword, placeCode);

        // 인증된 사용자 정보 로깅
        if (authentication != null) {
            String userId = authentication.getName();
            logger.info("Authenticated user ID: {}", userId);
            UserDTO userDto = userService.getUserInfo(userId);

            if (userDto != null) {
                logger.info("User info - ID: {}, Nickname: {}, Gender: {}, Birth Year: {}, Nation Code: {}",
                        userDto.getUserId(),
                        userDto.getNickname(),
                        userDto.getGender(),
                        userDto.getBirthYear(),
                        userDto.getNationCode());
            } else {
                logger.warn("User not found for ID: {}", userId);
            }
        } else {
            logger.warn("No authentication information available");
        }


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