package api.services.enablingServices.controller;

import api.services.enablingServices.exception.ResourceCannotBeDeletedException;
import api.services.enablingServices.exception.ResourceNotFoundException;
import api.services.enablingServices.model.Hospital;
import api.services.enablingServices.repository.DatasetsRepository;
import api.services.enablingServices.repository.HospitalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/enablingServices")
public class HospitalsController {
    @Autowired
    private HospitalsRepository hospitalsRepository;

    @Autowired
    private DatasetsRepository datasetsRepository;

    @GetMapping("/hospitals")
    public List<Hospital> getAllHospitals() {
        return hospitalsRepository.findAll();
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable(value = "id") UUID hospitalId)
            throws ResourceNotFoundException {
        Hospital hospital = hospitalsRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + hospitalId));
        return ResponseEntity.ok().body(hospital);
    }

    @PostMapping("/hospitals")
    public Hospital createHospital(@Valid @RequestBody Hospital hospital) {
        return hospitalsRepository.save(hospital);
    }

    @PatchMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable(value = "id") UUID hospitalId,
                                                   @Valid @RequestBody Hospital hospitalDetails) throws ResourceNotFoundException {
        Hospital hospital = hospitalsRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + hospitalId));

        hospital.setCode(hospitalDetails.getCode());
        hospital.setLabel(hospitalDetails.getLabel());
        final Hospital updatedHospital = hospitalsRepository.save(hospital);
        return ResponseEntity.ok(updatedHospital);
    }

    @DeleteMapping("/hospitals/{id}")
    public Map<String, Boolean> deleteHospital(@PathVariable(value = "id") UUID hospitalId)
            throws ResourceNotFoundException, ResourceCannotBeDeletedException {
        Hospital hospital = hospitalsRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found for this id :: " + hospitalId));

        if (!datasetsRepository.findByHospital_Id(hospitalId).isEmpty())
            throw new ResourceCannotBeDeletedException("The hospital cannot be deleted because one or more datasets are linked to it.");

        hospitalsRepository.delete(hospital);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}