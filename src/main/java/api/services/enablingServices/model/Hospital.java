package api.services.enablingServices.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "hospitals", uniqueConstraints={@UniqueConstraint(columnNames={"CODE"})})
public class Hospital {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "label", nullable = false)
    private String label;

    public Hospital() {

    }

    public Hospital(String code, String label) {
         this.code = code;
         this.label = label;
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