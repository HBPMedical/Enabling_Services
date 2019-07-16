package api.services.enablingServices.model;

import api.services.enablingServices.annotation.Unique;
import api.services.enablingServices.service.MipUsersService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "mip_users")
public class MipUser {

    @Null
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Size(max = 50)
    @Unique(service = MipUsersService.class, columnName = "username")
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    public MipUser() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}