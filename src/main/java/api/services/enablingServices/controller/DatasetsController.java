package api.services.enablingServices.controller;

import api.services.enablingServices.exception.ResourceNotFoundException;
import api.services.enablingServices.model.Dataset;
import api.services.enablingServices.repository.DatasetsRepository;
import api.services.enablingServices.repository.HospitalsRepository;
import api.services.enablingServices.repository.PathologiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/enablingServices")
public class DatasetsController {
    @Autowired
    private DatasetsRepository datasetsRepository;

    @Autowired
    private PathologiesRepository pathologiesRepository;

    @Autowired
    private HospitalsRepository hospitalsRepository;

    @GetMapping("/datasets")
    public List<Dataset> getAllDatasets() {
        return datasetsRepository.findAll();
    }

    @GetMapping("/datasets/{id}")
    public ResponseEntity<Dataset> getDatasetById(@PathVariable(value = "id") UUID datasetId)
            throws ResourceNotFoundException {
        Dataset dataset = datasetsRepository.findById(datasetId)
                .orElseThrow(() -> new ResourceNotFoundException("Dataset not found for this id :: " + datasetId));
        return ResponseEntity.ok().body(dataset);
    }

    @PostMapping("/datasets")
    public Dataset createDataset(@Valid @RequestBody Dataset dataset) {
        dataset = datasetsRepository.save(dataset);
        dataset.setPathology(pathologiesRepository.findById(dataset.getPathologyId()).get());
        dataset.setHospital(hospitalsRepository.findById(dataset.getHospitalId()).get());
        return dataset;
    }

    @PatchMapping("/datasets/{id}")
    public ResponseEntity<Dataset> updateDataset(@PathVariable(value = "id") UUID datasetId,
                                                 @Valid @RequestBody Dataset datasetDetails) throws ResourceNotFoundException {
        Dataset dataset = datasetsRepository.findById(datasetId)
                .orElseThrow(() -> new ResourceNotFoundException("Dataset not found for this id :: " + datasetId));

        dataset.setCode(datasetDetails.getCode());
        dataset.setLabel(datasetDetails.getLabel());
        dataset.setHospitalId(datasetDetails.getHospitalId());
        dataset.setPathologyId(datasetDetails.getPathologyId());
        final Dataset updatedDataset = datasetsRepository.save(dataset);
        return ResponseEntity.ok(updatedDataset);
    }

    @DeleteMapping("/datasets/{id}")
    public Map<String, Boolean> deleteDataset(@PathVariable(value = "id") UUID datasetId)
            throws ResourceNotFoundException {
        Dataset dataset = datasetsRepository.findById(datasetId)
                .orElseThrow(() -> new ResourceNotFoundException("Dataset not found for this id :: " + datasetId));

        datasetsRepository.delete(dataset);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}