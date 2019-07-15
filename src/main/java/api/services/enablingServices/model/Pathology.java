package api.services.enablingServices.model;

import api.services.enablingServices.annotation.Unique;
import api.services.enablingServices.service.PathologiesService;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.simple.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;


@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name = "pathologies", uniqueConstraints = {@UniqueConstraint(columnNames = {"CODE"})})
public class Pathology {

    @Null
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Size(max = 50)
    @Unique(service = PathologiesService.class, columnName = "code")
    @Column(name = "code", unique = true, nullable = false, length = 50)
    private String code;

    @Column(name = "label")
    private String label;

    @NotNull
    @Size(max = 50)
    @Column(name = "version", nullable = false, length = 50)
    private String version;

    @NotNull
    @Type(type = "jsonb")
    @Column(columnDefinition = "json")
    private JSONObject metadata;

    public Pathology() {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public JSONObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JSONObject metadata) {
        this.metadata = metadata;
    }


    @Override
    public String toString() {
        return "Pathology [id=" + id + ", code=" + code + ", label=" + label + ", version=" + version + ", metadata=" + metadata.toString() + "]";
    }

}