package com.b1aboa.wedug.controller;

import java.util.List;

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

	@GetMapping("/{userId}/favorites")
	public String showFavoritesPage(@PathVariable("userId") String userId, Model model) {
		List<Favorite> favoriteList = favoriteService.showAllFavorites(userId);
		model.addAttribute(favoriteList);

		return "favorites/favorites";
	}

}
