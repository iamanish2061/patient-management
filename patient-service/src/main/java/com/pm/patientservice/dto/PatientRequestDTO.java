package com.pm.patientservice.dto;

import com.pm.patientservice.constants.ValidationMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {

    @NotBlank(message = ValidationMessage.NAME + ValidationMessage.REQUIRED)
    @Size(max = 100, message = ValidationMessage.NAME + ValidationMessage.LENGTH_100)
    private String name;

    @NotBlank(message = ValidationMessage.EMAIL + ValidationMessage.REQUIRED)
    @Email(message = ValidationMessage.EMAIL + ValidationMessage.INVALID)
    private String email;

    @NotBlank(message = ValidationMessage.ADDRESS + ValidationMessage.REQUIRED)
    @Size(max = 255, message = ValidationMessage.ADDRESS + ValidationMessage.LENGTH_255)
    private String address;

    @NotBlank(message = ValidationMessage.DATE_OF_BIRTH + ValidationMessage.REQUIRED)
    private String dateOfBirth;

}
