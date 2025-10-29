package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientMapper {
   public static PatientResponseDTO  toDTO(Patient patient){
       PatientResponseDTO patientDto = new PatientResponseDTO();
       patientDto.setId(patient.getId().toString());
       patientDto.setName(patient.getName());
       patientDto.setEmail(patient.getEmail());
       patientDto.setAddress(patient.getAddress());
       patientDto.setDateOfBirth(patient.getDateOfBirth().toString());
       return patientDto;
   }
   public static Patient toModel(PatientRequestDTO patientRequestDTO){

       Patient patient = new Patient();
       patient.setName(patientRequestDTO.getName());
       patient.setAddress(patientRequestDTO.getAddress());
       patient.setEmail(patientRequestDTO.getEmail());
       patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
       patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRigisteredDate()));
       return patient;

   }
}
