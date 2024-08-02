package com.b1aboa.wedug.service;

import com.b1aboa.wedug.entity.Dept;
import com.b1aboa.wedug.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;

@Service
public class DeptService {
    // Logger 인스턴스 생성
    private static final Logger logger = Logger.getLogger(DeptService.class.getName());

    @Autowired
    private DeptRepository deptRepository;

    public List<Dept> getAllDepts() {
        return deptRepository.findAll();
    }

    public Dept saveDept(Dept dept) {
        return deptRepository.save(dept);
    }

    @PostConstruct
    public void logAllDepts() {
        // 데이터베이스에서 모든 부서 데이터 조회
        List<Dept> depts = deptRepository.findAll();
        // 각 부서 데이터를 로그에 출력
        depts.forEach(dept -> logger.info(dept.toString()));
    }
}
