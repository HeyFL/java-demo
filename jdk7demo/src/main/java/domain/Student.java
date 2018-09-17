/*
 * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
