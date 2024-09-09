package com.kenzi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Appointment {
    @Id
    private  Long id;
    private String clinicAddress;
    private Date appointmentDate;
    private LocalTime appointmentHeure;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
