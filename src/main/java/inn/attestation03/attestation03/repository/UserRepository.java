package inn.attestation03.attestation03.repository;

import inn.attestation03.attestation03.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
