package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/map")
    public String showMap(Model model) {
        model.addAttribute("apiKey", mapService.getKakaoMapsApiKey());
        return "map";
    }
}