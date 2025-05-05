package inn.attestation03.attestation03.service.impl;

import inn.attestation03.attestation03.model.dto.ProductDTO;
import inn.attestation03.attestation03.model.entity.Product;
import inn.attestation03.attestation03.repository.ProductRepository;
import inn.attestation03.attestation03.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = Product.builder()
                .nameOfProduct(dto.getNameOfProduct())
                .price(dto.getPrice())
                .softDeleted(false)
                .build();
        return toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setNameOfProduct(dto.getNameOfProduct());
                    product.setPrice(dto.getPrice());
                    return toDTO(productRepository.save(product));
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream()
                .filter(p -> !p.isSoftDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setSoftDeleted(true);
                    productRepository.save(product);
                }, () -> {
                    throw new RuntimeException("Product not found");
                });
    }

    private ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .nameOfProduct(product.getNameOfProduct())
                .price(product.getPrice())
                .build();
    }
}
