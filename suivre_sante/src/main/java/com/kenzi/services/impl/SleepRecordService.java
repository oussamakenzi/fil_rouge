package com.kenzi.services.impl;

import com.kenzi.models.SleepRecord;
import com.kenzi.repositories.SleepRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SleepRecordService {

    @Autowired
    private SleepRecordRepository sleepRecordRepository;

    public SleepRecord saveSleepRecord(SleepRecord sleepRecord) {
        return sleepRecordRepository.save(sleepRecord);
    }

    public List<SleepRecord> getSleepRecordsByUserIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return sleepRecordRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }

    public List<SleepRecord> getSleepRecordsByUserId(Long userId) {
        return sleepRecordRepository.findByUserId(userId);
    }

    public SleepRecord getSleepRecordById(Long id) {
        return sleepRecordRepository.findById(id).orElse(null);
    }

    public void deleteSleepRecord(Long id) {
        sleepRecordRepository.deleteById(id);
    }
}