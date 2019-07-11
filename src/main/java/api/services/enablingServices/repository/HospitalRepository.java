package api.services.enablingServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.services.enablingServices.model.Hospital;

import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID>{

}