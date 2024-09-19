package com.kenzi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Long id;
    private String clinicAddress;
    private Date appointmentDate;
    private LocalTime appointmentHeure;
    private Long userId;
}

