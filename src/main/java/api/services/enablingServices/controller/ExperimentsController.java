package api.services.enablingServices.controller;

import api.services.enablingServices.exception.ResourceCannotBeDeletedException;
import api.services.enablingServices.exception.ResourceNotFoundException;
import api.services.enablingServices.model.Experiment;
import api.services.enablingServices.repository.ExperimentsRepository;
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
public class ExperimentsController {
    @Autowired
    private ExperimentsRepository experimentsRepository;

    @GetMapping("/experiments")
    public List<Experiment> getAllExperiments() {
        return experimentsRepository.findAll();
    }

    @GetMapping("/experiments/{id}")
    public ResponseEntity<Experiment> getExperimentById(@PathVariable(value = "id") UUID experimentId)
            throws ResourceNotFoundException {
        Experiment experiment = experimentsRepository.findById(experimentId)
                .orElseThrow(() -> new ResourceNotFoundException("Experiment not found for this id :: " + experimentId));
        return ResponseEntity.ok().body(experiment);
    }

    @PostMapping("/experiments")
    public Experiment createExperiment(@Valid @RequestBody Experiment experiment) {
        return experimentsRepository.save(experiment);
    }

    @PatchMapping("/experiments/{id}")
    public ResponseEntity<Experiment> updateExperiment(@PathVariable(value = "id") UUID experimentId,
                                                       @Valid @RequestBody Experiment experimentDetails) throws ResourceNotFoundException {
        Experiment experiment = experimentsRepository.findById(experimentId)
                .orElseThrow(() -> new ResourceNotFoundException("Experiment not found for this id :: " + experimentId));

        experiment.setCode(experimentDetails.getCode());
        experiment.setLabel(experimentDetails.getLabel());
        experiment.setMipUserId(experimentDetails.getMipUserId());
        experiment.setAlgorithmCode(experimentDetails.getAlgorithmCode());
        experiment.setInputJson(experimentDetails.getInputJson());
        experiment.setResultJson(experimentDetails.getResultJson());
        final Experiment updatedExperiment = experimentsRepository.save(experiment);
        return ResponseEntity.ok(updatedExperiment);
    }

    @DeleteMapping("/experiments/{id}")
    public Map<String, Boolean> deleteExperiment(@PathVariable(value = "id") UUID experimentId)
            throws ResourceNotFoundException, ResourceCannotBeDeletedException {
        Experiment experiment = experimentsRepository.findById(experimentId)
                .orElseThrow(() -> new ResourceNotFoundException("Experiment not found for this id :: " + experimentId));

        experimentsRepository.delete(experiment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}