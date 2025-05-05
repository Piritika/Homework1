package inn.attestation03.attestation03.service;

import inn.attestation03.attestation03.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {
      ProductDTO createProduct(ProductDTO dto);
      ProductDTO updateProduct(Long id, ProductDTO dto);
      List<ProductDTO> getAll();
      void deleteProduct(Long id);
}
