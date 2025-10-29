package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        //@valid use to check the validation of the request that we write in DTO
        //@RequestBody convert the json to patientRequestDTO. Take the JSON (or XML / plain text) from the HTTP request body and convert it into this Java object.
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDTO));

    }
}
