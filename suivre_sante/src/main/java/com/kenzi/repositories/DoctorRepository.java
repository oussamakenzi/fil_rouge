package com.kenzi.repositories;

import com.kenzi.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);

    List<Doctor> findBySpecialite(String specialite);

    List<Doctor> findByHopital(String hopital);

    List<Doctor> findByLastNameContainingIgnoreCase(String lastName);

    List<Doctor> findByFirstNameContainingIgnoreCase(String firstName);

    List<Doctor> findBySpecialiteAndHopital(String specialite, String hopital);

    @Query("SELECT d FROM Doctor d WHERE d.experience >= :years")
    List<Doctor> findByExperienceGreaterThanEqual(@Param("years") String years);

    @Query("SELECT d FROM Doctor d WHERE LOWER(d.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(d.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Doctor> findByFirstNameOrLastNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT d FROM Doctor d WHERE d.specialite = :specialite ORDER BY d.experience DESC")
    List<Doctor> findTopDoctorsBySpecialite(@Param("specialite") String specialite);

    @Query(value = "SELECT * FROM doctor WHERE specialite = :specialite ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Doctor> findRandomDoctorBySpecialite(@Param("specialite") String specialite);

    @Query("SELECT COUNT(d) FROM Doctor d WHERE d.hopital = :hopital")
    long countDoctorsByHopital(@Param("hopital") String hopital);

    boolean existsByEmail(String email);
}