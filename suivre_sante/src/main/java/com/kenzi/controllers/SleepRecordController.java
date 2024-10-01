package com.kenzi.controllers;

import com.kenzi.models.SleepRecord;
import com.kenzi.services.impl.SleepRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sleep-records")
public class SleepRecordController {

    @Autowired
    private SleepRecordService sleepRecordService;

    @PostMapping
    public ResponseEntity<SleepRecord> createSleepRecord(@RequestBody SleepRecord sleepRecord) {
        return ResponseEntity.ok(sleepRecordService.saveSleepRecord(sleepRecord));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SleepRecord>> getSleepRecordsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(sleepRecordService.getSleepRecordsByUserId(userId));
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<SleepRecord>> getSleepRecordsByUserIdAndDateRange(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(sleepRecordService.getSleepRecordsByUserIdAndDateRange(userId, startDate, endDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SleepRecord> getSleepRecordById(@PathVariable Long id) {
        SleepRecord sleepRecord = sleepRecordService.getSleepRecordById(id);
        return sleepRecord != null ? ResponseEntity.ok(sleepRecord) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SleepRecord> updateSleepRecord(@PathVariable Long id, @RequestBody SleepRecord sleepRecord) {
        sleepRecord.setId(id);
        return ResponseEntity.ok(sleepRecordService.saveSleepRecord(sleepRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSleepRecord(@PathVariable Long id) {
        sleepRecordService.deleteSleepRecord(id);
        return ResponseEntity.noContent().build();
    }
}