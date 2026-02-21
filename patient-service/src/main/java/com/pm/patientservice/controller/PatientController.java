package com.pm.patientservice.controller;

import com.pm.patientservice.constants.MessageConstants;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.response.MessageParameter;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

@Tag(name = "Patient", description = "APIs of Patient")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "Get All", description = "Get all patient information")
    @GetMapping
    @MessageParameter(message = MessageConstants.SUCCESS_FETCH, source = MessageConstants.PATIENT)
    public List<PatientResponseDTO> getPatients() {
        return patientService.getPatients();
    }

    @Operation(summary = "Get One", description = "Get specific patient information via patient id")
    @GetMapping("/{patientId}")
    @MessageParameter(message = MessageConstants.SUCCESS_FETCH, source = MessageConstants.PATIENT)
    public PatientResponseDTO getPatients(@PathVariable UUID patientId) {
        return patientService.getPatientById(patientId);
    }

    @Operation(summary = "Create", description = "Register new patient")
    @PostMapping
    @MessageParameter(message = MessageConstants.SUCCESS_CREATE, source = MessageConstants.PATIENT)
    public PatientResponseDTO createPatient(@Valid @RequestBody PatientRequestDTO request) {
        return patientService.createPatient(request);
    }

    @Operation(summary = "Update", description = "Update existing patient")
    @PutMapping("/{patientId}")
    @MessageParameter(message = MessageConstants.SUCCESS_UPDATE, source = MessageConstants.PATIENT)
    public PatientResponseDTO updatePatient(
            @PathVariable UUID patientId,
            @Valid @RequestBody PatientRequestDTO request){
        return patientService.updatePatient(patientId, request);
    }

    @Operation(summary = "Delete", description = "Delete existing patient")
    @DeleteMapping("/{patientId}")
    @MessageParameter(message = MessageConstants.SUCCESS_DELETE, source = MessageConstants.PATIENT)
    public void deletePatient(@PathVariable UUID patientId){
        patientService.deletePatient(patientId);
    }

}
