/*
 * Copyright (c) 2023. Medavakkam, All rights reserved.
 */

package com.example.springboot3.jpa.h2.service;

import com.example.springboot3.jpa.h2.entity.Home;
import com.example.springboot3.jpa.h2.repository.HomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


//@ExtendWith(MockitoExtension.class)
public class HomeServiceTest {

    //    @Mock
    private HomeRepository homeRepository;
    //    @InjectMocks
    private HomeService homeService;

    @BeforeEach
    void init() {
        homeRepository = mock(HomeRepository.class);
        homeService = new HomeService(homeRepository);
    }

    @Test
    @DisplayName("Get all members Test")
    void test1() {
        when(homeRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(homeService.getFamily());
    }

    @Test
    @DisplayName("Get all members names Test")
    void test2() {
        List<Home> hList = new ArrayList<>();
        hList.add(new Home(1, "Aladdin", Home.REL.DAD));
        hList.add(new Home(2, "Jasmine", Home.REL.MOM));
        hList.add(new Home(3, "Abu", Home.REL.Son));
        when(homeRepository.findAll()).thenReturn(hList);

        assertTrue(homeService.getFamilyMemberNames().size() > 2);
    }

    @Test
    @DisplayName("Get all members id Test")
    void test3() {
        List<Home> hList = new ArrayList<>();
        hList.add(new Home(1, "Mac", Home.REL.DAD));
        hList.add(new Home(2, "Big Mac", Home.REL.MOM));
        hList.add(new Home(3, "Mac mini", Home.REL.Son));
        when(homeRepository.findAll()).thenReturn(hList);

        assertEquals(3, homeService.getFamilyMemberIds().get(2));
    }
}
