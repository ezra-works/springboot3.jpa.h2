package com.example.springboot3.jpa.h2.service;

import com.example.springboot3.jpa.h2.repository.HomeRepository;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public HomeRepository getHomeRepository() {
        return homeRepository;
    }
}
