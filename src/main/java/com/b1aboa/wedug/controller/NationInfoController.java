package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.entity.NationInfo;
import com.b1aboa.wedug.service.NationInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NationInfoController {

    private final NationInfoService nationInfoService;

    public NationInfoController(NationInfoService nationInfoService) {
        this.nationInfoService = nationInfoService;
    }

    @GetMapping("/nation-code")
    public ResponseEntity<List<NationInfo>> getNationCodes() {
        List<NationInfo> nations = nationInfoService.getAllNations();
        return ResponseEntity.ok(nations);
    }
}
