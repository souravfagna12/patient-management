package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<PatientResponseDTO>> getPatient(){
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @PostMapping("/createPatient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        //@valid use to check the validation of the request that we write in DTO
        //@validated check the validation with this file.
        //@RequestBody convert the json to patientRequestDTO. Take the JSON (or XML / plain text) from the HTTP request body and convert it into this Java object.
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDTO));

    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO=patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id){
        patientService.DeletePatient(id);
        return ResponseEntity.ok().body("patient deleted");
    }
}
