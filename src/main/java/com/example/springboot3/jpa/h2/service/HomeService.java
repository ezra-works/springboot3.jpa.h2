/*
 * Copyright (c) 2023. Medavakkam, All rights reserved.
 */

package com.example.springboot3.jpa.h2.service;

import com.example.springboot3.jpa.h2.entity.Home;
import com.example.springboot3.jpa.h2.repository.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<Home> getAllHomies() {
        return homeRepository.findAll();
    }

    public List<String> getAllHomiesNames() {
        return getAllHomies().stream().map(Home::getName).collect(Collectors.toList());
    }

    public List<Integer> getAllHomiesId() {
        return getAllHomies().stream().map(Home::getId).collect(Collectors.toList());
    }

    public List<Home> saveHomies(List<Home> homies) {
        return homeRepository.saveAll(homies);
    }

    public Home saveHomie(Home newHomie) {
        return homeRepository.save(newHomie);
    }
}
