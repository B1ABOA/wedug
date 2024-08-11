package com.b1aboa.wedug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    @GetMapping
    public String showFavoritesPage() {
        return "favorites/favorites";
    }

}
