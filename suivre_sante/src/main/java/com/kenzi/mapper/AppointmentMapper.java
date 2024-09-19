package com.kenzi.mapper;

import com.kenzi.dtos.AppointmentDto;
import com.kenzi.models.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    // Mapper de l'entité Appointment vers le DTO
    @Mapping(source = "user.id", target = "userId")
    AppointmentDto toDto(Appointment appointment);

    // Mapper du DTO vers l'entité Appointment
    @Mapping(source = "userId", target = "user.id")
    Appointment toEntity(AppointmentDto appointmentDto);
}
