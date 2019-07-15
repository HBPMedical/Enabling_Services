package api.services.enablingServices.repository;

import api.services.enablingServices.model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DatasetsRepository extends JpaRepository<Dataset, UUID> {
    Dataset findByCode(String code);
    List<Dataset> findByHospital_Id(UUID id);
    List<Dataset> findByPathology_Id(UUID id);

}