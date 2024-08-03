package com.b1aboa.wedug.controller;

import com.b1aboa.wedug.entity.Dept;
import com.b1aboa.wedug.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public List<Dept> getAllDepts() {
        return deptService.getAllDepts();
    }

    @PostMapping
    public Dept addDept(@RequestBody Dept dept) {
        return deptService.saveDept(dept);
    }
}
