package com.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {
    @NotBlank
    @Size(max = 100, message="name cannot exceed 100 characters")
    private String name;

    @NotBlank
    @Email(message="email should be valid")
    private String email;

    @NotBlank(message= "address should not be empty")
    private String address ;

    @NotBlank
    private String dateOfBirth;

    @NotBlank
    private String rigisteredDate;

}
