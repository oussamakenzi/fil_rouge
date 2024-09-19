package com.kenzi.controllers;


import com.kenzi.dtos.DoctorDto;
import com.kenzi.services.impl.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto DoctorDto) {
        DoctorDto savedDoctor = doctorService.saveDoctor(DoctorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto DoctorDto) {
        return doctorService.getDoctorById(id)
                .map(existingDoctor -> {
                    DoctorDto.setId(id);
                    return ResponseEntity.ok(doctorService.saveDoctor(DoctorDto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorService.getDoctorById(id).isPresent()) {
            doctorService.deleteDoctor(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DoctorDto> getDoctorByEmail(@PathVariable String email) {
        return doctorService.getDoctorByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialite/{specialite}")
    public ResponseEntity<List<DoctorDto>> getDoctorsBySpecialite(@PathVariable String specialite) {
        List<DoctorDto> doctors = doctorService.getDoctorsBySpecialite(specialite);
        return doctors.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(doctors);
    }

    @GetMapping("/hopital/{hopital}")
    public ResponseEntity<List<DoctorDto>> getDoctorsByHopital(@PathVariable String hopital) {
        List<DoctorDto> doctors = doctorService.getDoctorsByHopital(hopital);
        return doctors.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(doctors);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorDto>> searchDoctorsByName(@RequestParam String name) {
        List<DoctorDto> doctors = doctorService.searchDoctorsByName(name);
        return doctors.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(doctors);
    }

    @GetMapping("/specialite-hopital")
    public ResponseEntity<List<DoctorDto>> getDoctorsBySpecialiteAndHopital(
            @RequestParam String specialite, @RequestParam String hopital) {
        List<DoctorDto> doctors = doctorService.getDoctorsBySpecialiteAndHopital(specialite, hopital);
        return doctors.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(doctors);
    }

    @GetMapping("/experience")
    public ResponseEntity<List<DoctorDto>> getDoctorsByExperience(@RequestParam String years) {
        List<DoctorDto> doctors = doctorService.getDoctorsByExperience(years);
        return doctors.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(doctors);
    }

    @GetMapping("/top-doctors/{specialite}")
    public ResponseEntity<List<DoctorDto>> getTopDoctorsBySpecialite(@PathVariable String specialite) {
        List<DoctorDto> doctors = doctorService.getTopDoctorsBySpecialite(specialite);
        return doctors.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(doctors);
    }

    @GetMapping("/random/{specialite}")
    public ResponseEntity<DoctorDto> getRandomDoctorBySpecialite(@PathVariable String specialite) {
        return doctorService.getRandomDoctorBySpecialite(specialite)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count/{hopital}")
    public ResponseEntity<Long> countDoctorsByHopital(@PathVariable String hopital) {
        long count = doctorService.countDoctorsByHopital(hopital);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        boolean exists = doctorService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}