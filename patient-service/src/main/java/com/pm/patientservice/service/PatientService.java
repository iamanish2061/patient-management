package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {

    List<PatientResponseDTO> getPatients();

    PatientResponseDTO getPatientById(UUID patientId);

    PatientResponseDTO createPatient(PatientRequestDTO request);

    PatientResponseDTO updatePatient(UUID id, PatientRequestDTO request);

    void deletePatient(UUID id);

}
