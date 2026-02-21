package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient){
        return PatientResponseDTO.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .address(patient.getAddress())
                .email(patient.getEmail())
                .dateOfBirth(patient.getDateOfBirth().toString())
                .build();
    }

    public static Patient toModel(PatientRequestDTO request){
        return Patient.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .build();
    }

    public static Patient updateModel(Patient patient, PatientRequestDTO request){
        patient.setName(request.getName());
        patient.setAddress(request.getAddress());
        patient.setEmail(request.getEmail());
        patient.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        return patient;
    }
}
