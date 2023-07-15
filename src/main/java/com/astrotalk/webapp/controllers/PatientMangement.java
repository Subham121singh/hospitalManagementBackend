package com.astrotalk.webapp.controllers;

import com.astrotalk.webapp.entities.Patient;
import com.astrotalk.webapp.enums.Status;
import com.astrotalk.webapp.repositories.PatientRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class PatientMangement {

    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/api/admit")
    public ResponseEntity<Patient> createPatientRecord(@RequestBody Patient patient)
    {
            Patient savedPatient = patientRepository.save(patient);

            return ResponseEntity.ok().body(savedPatient);
    }

    @GetMapping("/api/patients")
    public ResponseEntity<List<Patient>> getPatients()
    {
        List<Patient> savedPatients = patientRepository.findAll();
        return ResponseEntity.ok().body(savedPatients);
    }

    @PatchMapping("/api/discharge/{patientId}")
    public ResponseEntity<Patient> dischargePatient(@PathVariable String patientId)
    {
        Patient patient = patientRepository.findByPatientId(patientId);
        if(patient!=null)
        {
            patient.setStatus(Status.DISCHARGED);
        }

        patientRepository.save(patient);
        return ResponseEntity.status(200).body(patient);
    }
}
