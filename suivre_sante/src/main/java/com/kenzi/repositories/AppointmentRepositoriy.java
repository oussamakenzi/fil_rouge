package com.kenzi.repositories;

import com.kenzi.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepositoriy extends JpaRepository<Appointment,Long> {
}
