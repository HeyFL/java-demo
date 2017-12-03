/*
* Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
* Use, Copy is subject to authorized license.
*/
package com.example.test.demo.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Student {
    private String name;
    private int age;
    private String sex;
}
