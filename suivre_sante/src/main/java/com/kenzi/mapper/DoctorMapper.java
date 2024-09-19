package com.kenzi.mapper;

import com.kenzi.dtos.DoctorDto;
import com.kenzi.models.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mapping(target = "certificationIds", expression = "java(doctor.getCertifications().stream().map(cert -> cert.getId()).toList())")
    @Mapping(target = "medicalRecordIds", expression = "java(doctor.getMedicalRecords().stream().map(record -> record.getId()).toList())")
    @Mapping(target = "appointmentIds", expression = "java(doctor.getAppointments().stream().map(app -> app.getId()).toList())")
    DoctorDto doctorToDTO(Doctor doctor);

    @Mapping(target = "certifications", ignore = true)
    @Mapping(target = "medicalRecords", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    Doctor dtoToDoctor(DoctorDto doctorDTO);

    List<DoctorDto> doctorsToDTOs(List<Doctor> doctors);
    List<Doctor> dtosToDooctors(List<DoctorDto> doctorDTOs);
}