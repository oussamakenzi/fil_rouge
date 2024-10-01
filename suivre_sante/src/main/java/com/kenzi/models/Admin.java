package com.kenzi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {


    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;




}
