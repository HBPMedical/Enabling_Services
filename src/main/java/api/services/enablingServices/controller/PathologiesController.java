package api.services.enablingServices.controller;

import api.services.enablingServices.exception.ResourceCannotBeDeletedException;
import api.services.enablingServices.exception.ResourceNotFoundException;
import api.services.enablingServices.model.Pathology;
import api.services.enablingServices.repository.DatasetsRepository;
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
public class PathologiesController {
    @Autowired
    private PathologiesRepository PathologiesRepository;

    @Autowired
    private DatasetsRepository datasetsRepository;

    @GetMapping("/pathologies")
    public List<Pathology> getAllPathologies() {
        return PathologiesRepository.findAll();
    }

    @GetMapping("/pathologies/{id}")
    public ResponseEntity<Pathology> getPathologyById(@PathVariable(value = "id") UUID pathologyId)
            throws ResourceNotFoundException {
        Pathology pathology = PathologiesRepository.findById(pathologyId)
                .orElseThrow(() -> new ResourceNotFoundException("Pathology not found for this id :: " + pathologyId));
        return ResponseEntity.ok().body(pathology);
    }

    @PostMapping("/pathologies")
    public Pathology createPathology(@Valid @RequestBody Pathology pathology) {
        return PathologiesRepository.save(pathology);
    }

    @PatchMapping("/pathologies/{id}")
    public ResponseEntity<Pathology> updatePathology(@PathVariable(value = "id") UUID pathologyId,
                                                   @Valid @RequestBody Pathology pathologyDetails) throws ResourceNotFoundException {
        Pathology pathology = PathologiesRepository.findById(pathologyId)
                .orElseThrow(() -> new ResourceNotFoundException("Pathology not found for this id :: " + pathologyId));

        pathology.setCode(pathologyDetails.getCode());
        pathology.setLabel(pathologyDetails.getLabel());
        pathology.setVersion(pathologyDetails.getVersion());
        pathology.setMetadata(pathology.getMetadata());
        final Pathology updatedPathology = PathologiesRepository.save(pathology);
        return ResponseEntity.ok(updatedPathology);
    }

    @DeleteMapping("/pathologies/{id}")
    public Map<String, Boolean> deletePathology(@PathVariable(value = "id") UUID pathologyId)
            throws ResourceNotFoundException, ResourceCannotBeDeletedException {
        Pathology pathology = PathologiesRepository.findById(pathologyId)
                .orElseThrow(() -> new ResourceNotFoundException("Pathology not found for this id :: " + pathologyId));

        if (!datasetsRepository.findByPathology_Id(pathologyId).isEmpty())
            throw new ResourceCannotBeDeletedException("The pathology cannot be deleted because one or more datasets are linked to it.");


        PathologiesRepository.delete(pathology);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}