package com.kenzi.models;

import com.kenzi.enumeration.SleepQuality;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
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

