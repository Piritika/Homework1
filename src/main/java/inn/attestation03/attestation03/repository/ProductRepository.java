package inn.attestation03.attestation03.repository;

import inn.attestation03.attestation03.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
