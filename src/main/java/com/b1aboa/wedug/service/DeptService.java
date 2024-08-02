package com.b1aboa.wedug.service;

import com.b1aboa.wedug.entity.Dept;
import com.b1aboa.wedug.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptRepository deptRepository;

    public List<Dept> getAllDepts() {
        return deptRepository.findAll();
    }

    public Dept saveDept(Dept dept) {
        return deptRepository.save(dept);
    }
}
