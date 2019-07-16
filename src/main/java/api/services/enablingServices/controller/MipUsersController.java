package api.services.enablingServices.controller;

import api.services.enablingServices.exception.ResourceCannotBeDeletedException;
import api.services.enablingServices.exception.ResourceNotFoundException;
import api.services.enablingServices.model.MipUser;
import api.services.enablingServices.repository.ExperimentsRepository;
import api.services.enablingServices.repository.MipUsersRepository;
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
public class MipUsersController {
    @Autowired
    private MipUsersRepository mipUsersRepository;

    @Autowired
    private ExperimentsRepository experimentsRepository;

    @GetMapping("/mipUsers")
    public List<MipUser> getAllMipUsers() {
        return mipUsersRepository.findAll();
    }

    @GetMapping("/mipUsers/{id}")
    public ResponseEntity<MipUser> getMipUserById(@PathVariable(value = "id") UUID mipUserId)
            throws ResourceNotFoundException {
        MipUser mipUser = mipUsersRepository.findById(mipUserId)
                .orElseThrow(() -> new ResourceNotFoundException("MipUser not found for this id :: " + mipUserId));
        return ResponseEntity.ok().body(mipUser);
    }

    @PostMapping("/mipUsers")
    public MipUser createMipUser(@Valid @RequestBody MipUser mipUser) {
        return mipUsersRepository.save(mipUser);
    }

    @PatchMapping("/mipUsers/{id}")
    public ResponseEntity<MipUser> updateMipUser(@PathVariable(value = "id") UUID mipUserId,
                                                 @Valid @RequestBody MipUser mipUserDetails) throws ResourceNotFoundException {
        MipUser mipUser = mipUsersRepository.findById(mipUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Mip User not found for this id :: " + mipUserId));

        mipUser.setUsername(mipUserDetails.getUsername());

        final MipUser updatedMipUser = mipUsersRepository.save(mipUser);
        return ResponseEntity.ok(updatedMipUser);
    }

    @DeleteMapping("/mipUsers/{id}")
    public Map<String, Boolean> deleteMipUser(@PathVariable(value = "id") UUID mipUserId)
            throws ResourceNotFoundException, ResourceCannotBeDeletedException {
        MipUser mipUser = mipUsersRepository.findById(mipUserId)
                .orElseThrow(() -> new ResourceNotFoundException("MipUser not found for this id :: " + mipUserId));

        // TODO Delete all experiments?
        if (!experimentsRepository.findByMipUserId(mipUserId).isEmpty())
            throw new ResourceCannotBeDeletedException("The mip user cannot be deleted because one or more experiments are linked to it.");

        mipUsersRepository.delete(mipUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}