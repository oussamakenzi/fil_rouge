package com.kenzi.models;

import com.kenzi.enumeration.SleepQuality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SleepRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime sleepStartTime;
    @Enumerated(EnumType.STRING)
    private SleepQuality sleepQuality;
    private LocalDateTime sleepEndTime;
    private LocalDate date;
    private int sleepDuration; // en minutes
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




}

