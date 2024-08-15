package com.b1aboa.wedug.service;

import com.b1aboa.wedug.entity.NationInfo;
import com.b1aboa.wedug.repository.NationInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationInfoServiceImpl implements NationInfoService {

    private final NationInfoRepository nationInfoRepository;

    public NationInfoServiceImpl(NationInfoRepository nationInfoRepository) {
        this.nationInfoRepository = nationInfoRepository;
    }

    @Override
    public List<NationInfo> getAllNations() {
        return nationInfoRepository.findAll();
    }
}