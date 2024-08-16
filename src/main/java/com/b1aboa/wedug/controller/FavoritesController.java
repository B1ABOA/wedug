package com.b1aboa.wedug.controller;

import java.util.List;

import com.b1aboa.wedug.dto.FavoriteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.b1aboa.wedug.entity.Favorite;
import com.b1aboa.wedug.service.FavoriteService;

@Controller
@RequestMapping("/api/favorites")
public class FavoritesController {

    @Autowired
    private FavoriteService favoriteService;

    @Operation(summary = "즐겨찾기 페이지 요청",
            description = "userId로 즐겨찾기 리스트 반환")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved favorites"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @GetMapping
    @ResponseBody
    public List<FavoriteDTO> showFavoriteList(@RequestParam(value = "userId", required = true) String userId) {
        return favoriteService.showAllFavorites(userId);
    }

    @DeleteMapping("/places")
    @ResponseBody
    public boolean deleteUserFavorite(@RequestParam(value = "userId", required = true) String userId,
                                      @RequestParam(value = "placeId", required = true) long placeId) {
        if (favoriteService.deleteUserFavorite(userId, placeId) > 0) {
            return true;
        }
        return false;
    }

    @GetMapping("/check/{userId}/{placeId}")
    @ResponseBody
    public boolean isUserFavorite(@PathVariable("userId") String userId, @PathVariable("placeId") long placeId) {
        if (favoriteService.isUserFavorite(userId, placeId))
            return true;
        return false;
    }

    @GetMapping("/places")
    @ResponseBody
    public boolean addUserFavorite(@RequestParam(value = "userId", required = true) String userId,
                                   @RequestParam(value = "placeId", required = true) long placeId) {
        System.out.println("진입!!!");
        if (favoriteService.addUserFavorite(userId, placeId) > 0)
            return true;
        return false;
    }
}
