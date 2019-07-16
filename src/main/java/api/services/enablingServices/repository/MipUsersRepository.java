package api.services.enablingServices.repository;

import api.services.enablingServices.model.MipUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MipUsersRepository extends JpaRepository<MipUser, UUID> {
    MipUser findByUsername(String username);
}