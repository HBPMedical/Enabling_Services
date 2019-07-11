package api.services.enablingServices.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.services.enablingServices.exception.ResourceNotFoundException;
import api.services.enablingServices.model.Hospital;
import api.services.enablingServices.repository.HospitalRepository;

@RestController
@RequestMapping("/api/enablingServices")
public class HospitalController {
    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping("/hospitals")
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable(value = "id") UUID hospitalId)
            throws ResourceNotFoundException {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + hospitalId));
        return ResponseEntity.ok().body(hospital);
    }

    @PostMapping("/hospitals")
    public Hospital createHospital(@Valid @RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @PutMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable(value = "id") UUID hospitalId,
                                                   @Valid @RequestBody Hospital hospitalDetails) throws ResourceNotFoundException {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + hospitalId));

        hospital.setCode(hospitalDetails.getCode());
        hospital.setLabel(hospitalDetails.getLabel());
        final Hospital updatedHospital = hospitalRepository.save(hospital);
        return ResponseEntity.ok(updatedHospital);
    }

    @DeleteMapping("/hospitals/{id}")
    public Map<String, Boolean> deleteHospital(@PathVariable(value = "id") UUID hospitalId)
            throws ResourceNotFoundException {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + hospitalId));

        hospitalRepository.delete(hospital);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}