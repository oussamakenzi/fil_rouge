package com.kenzi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
