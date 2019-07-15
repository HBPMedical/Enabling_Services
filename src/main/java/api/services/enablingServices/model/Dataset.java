package api.services.enablingServices.model;

import api.services.enablingServices.annotation.ReferenceExists;
import api.services.enablingServices.annotation.Unique;
import api.services.enablingServices.repository.HospitalsRepository;
import api.services.enablingServices.repository.PathologiesRepository;
import api.services.enablingServices.service.DatasetsService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "datasets", uniqueConstraints = {@UniqueConstraint(columnNames = {"CODE"})})
public class Dataset {

    @Null
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Size(max=50)
    @Unique(service = DatasetsService.class, columnName = "code")
    @Column(name = "code", unique = true, nullable = false, length = 50)
    private String code;

    @Column(name = "label")
    private String label;

    @NotNull
    @ReferenceExists(repository = HospitalsRepository.class)
    @Column(name = "hospital_id")
    private UUID hospital_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_id", referencedColumnName="id",  insertable = false, updatable = false)
    private Hospital hospital;

    @NotNull
    @ReferenceExists(repository = PathologiesRepository.class)
    @Column(name = "pathology_id")
    private UUID pathology_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pathology_id", referencedColumnName="id", insertable = false, updatable = false)
    private Pathology pathology;

    public Dataset() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public UUID getHospitalId() {
        return hospital_id;
    }

    public void setHospitalId(UUID hospital_id) {
        this.hospital_id = hospital_id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public UUID getPathologyId() {
        return pathology_id;
    }

    public void setPathologyId(UUID pathology_id) {
        this.pathology_id = pathology_id;
    }

    public Pathology getPathology() {
        return pathology;
    }

    public void setPathology(Pathology pathology) {
        this.pathology = pathology;
    }



    @Override
    public String toString() {
        return "Dataset [id=" + id + ", code=" + code + ", label=" + label + "]";
    }

}