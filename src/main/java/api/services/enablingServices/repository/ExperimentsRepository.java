package api.services.enablingServices.repository;

import api.services.enablingServices.model.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExperimentsRepository extends JpaRepository<Experiment, UUID> {
    Experiment findByCode(String code);

    List<Experiment> findByMipUserId(UUID id);
}