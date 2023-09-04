package kz.shop.auto_parts.services;

import kz.shop.auto_parts.entities.ProductEntity;
import kz.shop.auto_parts.entities.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductEntity> getAllProducts();

    ProductEntity getById(Long id);

    List<ProductEntity> getAllByAmountGreaterThan(Integer amount);

    ProductDto createProduct(ProductDto productDto);

    void deleteProductById(Long id);

    List<ProductEntity> getAllByNameStartingWithOrderByAmountDesc(String startChars);

    List<ProductEntity> searchProducts(String keyword);
}
