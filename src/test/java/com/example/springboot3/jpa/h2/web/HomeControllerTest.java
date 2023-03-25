/*
 * Copyright (c) 2023. Medavakkam, All rights reserved.
 */

package com.example.springboot3.jpa.h2.web;

import com.example.springboot3.jpa.h2.entity.Home;
import com.example.springboot3.jpa.h2.records.NewHomie;
import com.example.springboot3.jpa.h2.service.HomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    @MockBean
    private HomeService homeService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get default resource [] Test")
    void test1() throws Exception {
        when(homeService.getAllHomies()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/homies"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Get default resource [].* Test")
    void test2() throws Exception {

        List<Home> homeList = new ArrayList<>();
        homeList.add(new Home(1, "Pac", Home.REL.DAD));
        when(homeService.getAllHomies()).thenReturn(homeList);

        mockMvc.perform(get("/homies"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].name").value("Pac"));
    }

    @Test
    @DisplayName("Get homies names Test")
    void test3() throws Exception {
        when(homeService.getAllHomiesNames()).thenReturn(Arrays.asList("Mac", "Big Mac", "Mac mini"));

        mockMvc.perform(get("/homies/names"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Post new homie Test")
    void test4() throws Exception {

        Home h1 = new Home(4, "Mel", Home.REL.Daughter);
        NewHomie n1 = new NewHomie(h1.getName(), h1.getRelation());
        when(homeService.saveHomie(any())).thenReturn(h1);

        mockMvc.perform(post("/homies/add")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\": \"Mel\", \"REL\": \"Daughter\"}"))
                        .content(new ObjectMapper().writeValueAsString(n1)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(4));

    }
}
