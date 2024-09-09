package com.kenzi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "user")
    List<Appointment> appointments;
    // Relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MentalHealth> mentalHealthRecords;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SleepRecord> sleepRecords;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Nutrition> nutritionRecords;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicationReminders;

   /* @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
*/

}