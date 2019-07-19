package api.services.enablingServices.model;

import api.services.enablingServices.annotation.ReferenceExists;
import api.services.enablingServices.annotation.Unique;
import api.services.enablingServices.repository.MipUsersRepository;
import api.services.enablingServices.service.ExperimentsService;
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
@Table(name = "experiments")
public class Experiment {

    @Null
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Size(max = 50)
    @Unique(service = ExperimentsService.class, columnName = "code")
    @Column(name = "code", unique = true, nullable = false, length = 50)
    private String code;

    @Column(name = "label")
    private String label;

    @NotNull
    @ReferenceExists(repository = MipUsersRepository.class)
    @Column(name = "mip_user_id", nullable = false)
    private UUID mipUserId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mip_user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MipUser mipUser;

    @NotNull
    @Size(max = 50)
    @Column(name = "algorithm_code", nullable = false, length = 50)
    private String algorithmCode;

    @NotNull
    @Type(type = "jsonb")
    @Column(name = "input_json", columnDefinition = "json")
    private JSONObject inputJson;

    @NotNull
    @Type(type = "jsonb")
    @Column(name = "result_json", columnDefinition = "json")
    private JSONObject resultJson;

    public Experiment() {
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

    public UUID getMipUserId() {
        return mipUserId;
    }

    public void setMipUserId(UUID mipUserId) {
        this.mipUserId = mipUserId;
    }

    public MipUser getMipUser() {
        return mipUser;
    }

    public void setMipUser(MipUser mipUser) {
        this.mipUser = mipUser;
    }

    public String getAlgorithmCode() {
        return algorithmCode;
    }

    public void setAlgorithmCode(String algorithmCode) {
        this.algorithmCode = algorithmCode;
    }

    public JSONObject getInputJson() {
        return inputJson;
    }

    public void setInputJson(JSONObject inputJson) {
        this.inputJson = inputJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }

    public void setResultJson(JSONObject resultJson) {
        this.resultJson = resultJson;
    }
}