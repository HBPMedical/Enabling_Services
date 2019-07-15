package api.services.enablingServices.repository;

import api.services.enablingServices.model.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PathologiesRepository extends JpaRepository<Pathology, UUID>{
    Pathology findByCode(String code);
}