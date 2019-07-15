package api.services.enablingServices.model;

import api.services.enablingServices.annotation.Unique;
import api.services.enablingServices.service.HospitalsService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "hospitals", uniqueConstraints = {@UniqueConstraint(columnNames = {"CODE"})})
public class Hospital {

    @Null
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Size(max = 50)
    @Unique(service = HospitalsService.class, columnName = "code")
    @Column(name = "code", unique = true, nullable = false, length = 50)
    private String code;

    @Column(name = "label")
    private String label;

    public Hospital() {
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

    @Override
    public String toString() {
        return "Hospital [id=" + id + ", code=" + code + ", label=" + label + "]";
    }

}