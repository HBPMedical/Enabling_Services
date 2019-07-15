package api.services.enablingServices.repository;

import api.services.enablingServices.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalsRepository extends JpaRepository<Hospital, UUID> {
    Hospital findByCode(String code);
}