package com.kenzi.services.impl;

import com.kenzi.dtos.AppointmentDto;
import com.kenzi.mapper.AppointmentMapper;
import com.kenzi.models.Appointment;
import com.kenzi.models.User;
import com.kenzi.repositories.AppointmentRepositoriy;
import com.kenzi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final UserRepository userRepository;
    private final AppointmentMapper appointmentMapper;
    @Autowired
    private  final AppointmentRepositoriy appointmentRepositoriy;

    public AppointmentService(UserRepository userRepository, AppointmentMapper appointmentMapper, AppointmentRepositoriy appointmentRepositoriy) {
        this.userRepository = userRepository;
        this.appointmentMapper = appointmentMapper;
        this.appointmentRepositoriy = appointmentRepositoriy;
    }

    // Sauvegarder un rendez-vous
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        User user = userRepository.findById(appointmentDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Appointment appointment = appointmentMapper.toEntity(appointmentDto);
        appointment.setUser(user); // Assigner l'utilisateur
        Appointment savedAppointment = appointmentRepositoriy.save(appointment);
        return appointmentMapper.toDto(savedAppointment);
    }

    // Récupérer tous les rendez-vous pour un utilisateur donné
    public List<AppointmentDto> getAppointmentsForUser(Long userId) {
        return appointmentRepositoriy.findByUserId(userId)
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    // Supprimer un rendez-vous
    public void deleteAppointment(Long id) {
        appointmentRepositoriy.deleteById(id);
    }
}
