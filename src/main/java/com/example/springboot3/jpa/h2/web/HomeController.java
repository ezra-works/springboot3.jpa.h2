/*
 * Copyright (c) 2023. Medavakkam, All rights reserved.
 */

package com.example.springboot3.jpa.h2.web;

import com.example.springboot3.jpa.h2.entity.Home;
import com.example.springboot3.jpa.h2.records.NewHomie;
import com.example.springboot3.jpa.h2.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/homies")
public class HomeController {
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("")
    List<Home> showHome() {

        List<Home> all = homeService.getAllHomies();
        if (all.size() == 0) {
            List<Home> addHomies = new ArrayList<>();
            Home h1 = new Home();
            h1.setName("Harry");
            h1.setRelation(Home.REL.DAD);

            Home h2 = new Home();
            h2.setName("Marry");
            h2.setRelation(Home.REL.MOM);

            Home h3 = new Home();
            h3.setName("Barry");
            h3.setRelation(Home.REL.Son);

            addHomies.add(h1);
            addHomies.add(h2);
            addHomies.add(h3);

            //save the homies
            all = homeService.saveHomies(addHomies);
        }
        return all;
    }

    @GetMapping("/names")
    List<String> getHomiesNames() {
        return homeService.getAllHomiesNames();
    }

    @PostMapping("/add")
    Home add(@RequestBody NewHomie body) {
        Home h1 = new Home();
        h1.setName(body.name());
        h1.setRelation(body.relation());

        return homeService.saveHomie(h1);
    }
}
