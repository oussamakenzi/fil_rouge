package com.kenzi.auth;

import com.kenzi.config.JwtService;
import com.kenzi.models.User;
import com.kenzi.models.Patient;
import com.kenzi.models.Doctor;
import com.kenzi.models.Admin;
import com.kenzi.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {
        User user;
        switch (request.getRole()) {
            case PATIENT:
                Patient patient = new Patient();
                patient.setDateOfBirth(request.getDateOfBirth());
                patient.setMedicalHistory(request.getMedicalHistory());
                patient.setInsuranceInfo(request.getInsuranceInfo());
                user = patient;
                break;
            case DOCTOR:
                Doctor doctor = new Doctor();
                doctor.setTelephone(request.getTelephone());
                doctor.setSpecialite(request.getSpecialite());
                doctor.setAdresse(request.getAdresse());
                doctor.setDateNaissance(request.getDateOfBirth());
                doctor.setGenre(request.getGenre());
                doctor.setExperience(request.getExperience());
                doctor.setHopital(request.getHopital());
                doctor.setBiographie(request.getBiographie());
                doctor.setPhotoProfil(request.getPhotoProfil());
                user = doctor;
                break;
            case ADMIN:
                user = new Admin();
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }

        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userService.saveUser(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userService.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}