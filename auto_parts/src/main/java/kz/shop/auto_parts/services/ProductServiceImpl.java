package kz.shop.auto_parts.services;

import kz.shop.auto_parts.entities.ProductEntity;
import kz.shop.auto_parts.entities.dto.ProductDto;
import kz.shop.auto_parts.exceptions.ProductNotFoundException;
import kz.shop.auto_parts.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getById(Long id) {
        return productRepository.getProductEntityByProductId(id)
                .orElseThrow(() -> new ProductNotFoundException("There is no such product with that id", "404"));
    }

    @Override
    public List<ProductEntity> getAllByAmountGreaterThan(Integer amount) {
        return productRepository.getAllByAmountGreaterThan(amount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity product = new ProductEntity();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setAmount(productDto.getAmount());
        product.setDescription(productDto.getDescription());
        ProductEntity savedProduct = productRepository.save(product);

        ProductDto newProductDto = new ProductDto();
        newProductDto.setName(savedProduct.getName());
        newProductDto.setAmount(savedProduct.getAmount());
        newProductDto.setPrice(savedProduct.getPrice());
        newProductDto.setDescription(savedProduct.getDescription());
        return newProductDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductEntity> getAllByNameStartingWithOrderByAmountDesc(String startChars) {
        return productRepository.getAllByNameStartingWithOrderByAmountDesc(startChars);
    }

    @Override
    public List<ProductEntity> searchProducts(String keyword) {
        return productRepository.findByDescriptionContainingIgnoreCase(keyword);
    }
}
