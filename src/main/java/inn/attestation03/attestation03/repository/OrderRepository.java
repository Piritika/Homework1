package inn.attestation03.attestation03.repository;

import inn.attestation03.attestation03.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}