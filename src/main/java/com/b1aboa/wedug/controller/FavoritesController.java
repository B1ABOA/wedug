package com.b1aboa.wedug.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b1aboa.wedug.entity.Favorite;
import com.b1aboa.wedug.service.FavoriteService;

@Controller
@RequestMapping("/api/users")
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
	@GetMapping("/{userId}/favorites")
	public String showFavoritesPage(
			@PathVariable("userId") String userId, Model model
	) {
		List<Favorite> favoriteList = favoriteService.showAllFavorites(userId);
		model.addAttribute(favoriteList);

		return "favorites/favorites";
	}

}
