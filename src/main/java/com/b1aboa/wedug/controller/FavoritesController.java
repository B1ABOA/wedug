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
    @GetMapping("/{userId}")
    @ResponseBody
    public List<FavoriteDTO> showFavoriteList(@PathVariable("userId") String userId) {
        return favoriteService.showAllFavorites(userId);
    }

    @DeleteMapping("/{userId}/places/{placeId}")
    @ResponseBody
    public boolean deleteUserFavorite(@PathVariable("userId") String userId, @PathVariable("placeId") long placeId) {
        if (favoriteService.deleteUserFavorite(userId, placeId) > 0)
            return true;
        return false;
    }
}
