package com.astrotalk.webapp.repositories;

import com.astrotalk.webapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByPatientId(String patientId);
}

