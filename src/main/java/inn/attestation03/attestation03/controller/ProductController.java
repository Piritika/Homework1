package inn.attestation03.attestation03.controller;

import inn.attestation03.attestation03.model.dto.ProductDTO;
import inn.attestation03.attestation03.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO dto) {
        log.info("start createProduct");
        productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        log.info("start getAllProducts");
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        log.info("start updateProduct");
        productService.updateProduct(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteProduct(@PathVariable Long id) {
        log.info("start softDeleteProduct");
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
