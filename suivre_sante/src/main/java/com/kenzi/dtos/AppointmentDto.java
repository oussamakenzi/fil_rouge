package com.kenzi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Long id;
    private String clinicAddress;
    private LocalDate appointmentDate;
    private LocalTime appointmentHeure;
    private Long userId;
}

