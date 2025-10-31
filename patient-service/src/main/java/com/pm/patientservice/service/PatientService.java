package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotfoundError;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients= patientRepository.findAll();
        return patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
    }
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("a patient with this email id already exist"+patientRequestDTO.getEmail());
        }
        Patient patient= PatientMapper.toModel(patientRequestDTO);
        patientRepository.saveAndFlush(patient);
        return PatientMapper.toDTO(patient);
    }
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        Patient patient= patientRepository.findById(id).orElseThrow(()->new PatientNotfoundError("patient for this id is not found."));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("a patient with this email id already exist"+patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        Patient upadatedPatient=patientRepository.save(patient);
        return PatientMapper.toDTO(upadatedPatient);

    }
    public void DeletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}
