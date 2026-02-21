package com.pm.patientservice.service.impl;

import com.pm.patientservice.config.CustomMessageSource;
import com.pm.patientservice.constants.MessageConstants;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.custom.ConflictException;
import com.pm.patientservice.exception.custom.NotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final CustomMessageSource customMessageSource;

    @Override
    public List<PatientResponseDTO> getPatients() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

    @Override
    public PatientResponseDTO getPatientById(UUID patientId) {
        return PatientMapper.toDTO(getPatient(patientId));
    }

    @Override
    @Transactional
    public PatientResponseDTO createPatient(PatientRequestDTO request) {
        checkEmailUnique(request.getEmail(), null);
        Patient patient = PatientMapper.toModel(request);
        patient.setRegisteredDate(LocalDate.now());
        return PatientMapper.toDTO(patientRepository.save(patient));
    }

    @Override
    @Transactional
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO request) {
        Patient patient = getPatient(id);
        checkEmailUnique(request.getEmail(), id);
        Patient updatedPatient = PatientMapper.updateModel(patient, request);
        return PatientMapper.toDTO(patientRepository.save(updatedPatient));
    }

    @Override
    @Transactional
    public void deletePatient(UUID id) {
        Patient patient = getPatient(id);
        patientRepository.delete(patient);
    }


    private void checkEmailUnique(String email, UUID id) {
        boolean exists = (id == null) ?
                patientRepository.existsByEmail(email) : patientRepository.existsByEmailAndIdNot(email, id);

        if(exists)
            throw new ConflictException(customMessageSource.get(MessageConstants.EXISTS_ALREADY,
                    customMessageSource.get(MessageConstants.EMAIL)));
    }

    private Patient getPatient(UUID id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new NotFoundException(customMessageSource.get(MessageConstants.NOT_FOUND,
                        customMessageSource.get(MessageConstants.PATIENT)))
        );
    }
}
