/*
 * Copyright (c) 2023. Medavakkam, All rights reserved.
 */

package com.example.springboot3.jpa.h2.web;

import com.example.springboot3.jpa.h2.entity.Home;
import com.example.springboot3.jpa.h2.records.NewMember;
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
        when(homeService.getFamily()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/members"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Get default resource [].* Test")
    void test2() throws Exception {

        List<Home> homeList = new ArrayList<>();
        homeList.add(new Home(1, "Pac", Home.REL.DAD));
        when(homeService.getFamily()).thenReturn(homeList);

        mockMvc.perform(get("/members"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].name").value("Pac"));
    }

    @Test
    @DisplayName("Get members names Test")
    void test3() throws Exception {
        when(homeService.getFamilyMemberNames()).thenReturn(Arrays.asList("Mac", "Big Mac", "Mac mini"));

        mockMvc.perform(get("/members/names"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Post new member Test")
    void test4() throws Exception {

        Home h1 = new Home(4, "Mel", Home.REL.Daughter);
        NewMember n1 = new NewMember(h1.getName(), h1.getRelation());
        when(homeService.saveMember(any())).thenReturn(h1);

        mockMvc.perform(post("/members/add")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\": \"Mel\", \"REL\": \"Daughter\"}"))
                        .content(new ObjectMapper().writeValueAsString(n1)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(4));

    }
}
