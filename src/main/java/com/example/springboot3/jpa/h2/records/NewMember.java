/*
 * Copyright (c) 2023. Medavakkam, All rights reserved.
 */

package com.example.springboot3.jpa.h2.records;

import com.example.springboot3.jpa.h2.entity.Home;

public record NewMember(String name, Home.REL relation) {
}
