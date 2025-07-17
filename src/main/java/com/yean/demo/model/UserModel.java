package com.yean.demo.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserModel {

    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String email;
    private String role = "user"; // Default role is 'user'
}
