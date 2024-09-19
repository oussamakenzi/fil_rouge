package com.kenzi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private int age;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
