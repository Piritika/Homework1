package inn.attestation03.attestation03;

import inn.attestation03.attestation03.model.dto.ProductDTO;
import inn.attestation03.attestation03.model.entity.Product;
import inn.attestation03.attestation03.repository.ProductRepository;
import inn.attestation03.attestation03.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void testCreateProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setNameOfProduct("Rose");
        dto.setPrice(10);
        productService.createProduct(dto);

        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Rose", products.get(0).getNameOfProduct());
    }

    @Test
    void testUpdateProduct() {
        Product product = productRepository.save(Product.builder().nameOfProduct("Tulip").price(15).build());
        ProductDTO dto = new ProductDTO();
        dto.setNameOfProduct("Lily");
        dto.setPrice(20);

        productService.updateProduct(product.getId(), dto);
        Assertions.assertEquals("Lily", productRepository.findById(product.getId()).orElseThrow().getNameOfProduct());
    }

    @Test
    void testSoftDeleteProduct() {
        Product product = productRepository.save(Product.builder().nameOfProduct("DeleteMe").price(1).build());
        productService.deleteProduct(product.getId());

        Assertions.assertTrue(productRepository.findById(product.getId()).orElseThrow().isSoftDeleted());
    }

    @Test
    void testGetProductsOnlyNonDeleted() {
        productRepository.save(Product.builder().nameOfProduct("A").price(10).softDeleted(false).build());
        productRepository.save(Product.builder().nameOfProduct("B").price(15).softDeleted(true).build());

        List<ProductDTO> result = productService.getAll();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("A", result.get(0).getNameOfProduct());
    }
}