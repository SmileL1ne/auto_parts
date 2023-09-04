package kz.shop.auto_parts.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import kz.shop.auto_parts.entities.ProductEntity;
import kz.shop.auto_parts.entities.dto.ProductDto;
import kz.shop.auto_parts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save-product")
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ProductEntity> get(@RequestParam @Positive Long id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get-all-by-start-string")
    public ResponseEntity<List<ProductEntity>> getAllByName(@RequestParam @NotBlank String chars) {
        return new ResponseEntity<>(productService.
                getAllByNameStartingWithOrderByAmountDesc(chars), HttpStatus.OK);
    }

    @GetMapping("/get-all-more-than-amount")
    public ResponseEntity<List<ProductEntity>> getAllByAmount(@RequestParam @Positive Integer amount) {
        return new ResponseEntity<>(productService.getAllByAmountGreaterThan(amount), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{productId}")
    public ResponseEntity<Void> deleteById(@PathVariable("productId") @Positive Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

}
