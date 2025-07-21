package com.yean.demo.service;

import com.yean.demo.entity.Product;
import com.yean.demo.model.BaseResponseModel;
import com.yean.demo.model.BaseResponseWithDataModel;
import com.yean.demo.model.ProductModel;
import com.yean.demo.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<BaseResponseWithDataModel>> listProducts() {
        List<Product> products = productRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonList(new BaseResponseWithDataModel(
                        "Products retrieved successfully",
                        200, products)));
    }

    public ResponseEntity<BaseResponseWithDataModel> getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        return product.map(value -> ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("Product found successfully", 200, value))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponseWithDataModel("Product not found", 404, null)));

    }

    public ResponseEntity<BaseResponseModel> createProduct(ProductModel product) {
        Product productEntity = new Product();

        productEntity.setProductName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setCreateAt(String.valueOf(System.currentTimeMillis()));
        productEntity.setUpdateAt(String.valueOf(System.currentTimeMillis()));

        productRepository.save(productEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel( "success", 200));
    }

    public ResponseEntity<BaseResponseModel> updateProduct(Long productId, ProductModel product) {
        Optional<Product> existing = productRepository.findById(productId);

        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseModel("Product not found", 404));
        }

        Product updatedProduct = existing.get();

        updatedProduct.setProductName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setUpdateAt(String.valueOf(System.currentTimeMillis()));

        productRepository.save(updatedProduct);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel( "success", 200));
    }

    public ResponseEntity<BaseResponseModel> deleteProduct(Long productId) {
        Optional<Product> existing = productRepository.findById(productId);

        if (!productRepository.existsById(productId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseModel("Product not found", 404));
        }
        productRepository.deleteById(productId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("Product deleted successfully", 200));
    }

    public ResponseEntity<BaseResponseWithDataModel> searchProducts(String name, double minPrice, double maxPrice) {
        String formattedName = name != null ?
                name.toLowerCase()
                : null;

        List<Product> product = productRepository.findProductsWithFilters(formattedName , minPrice, maxPrice);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseWithDataModel("Product not found", 404, null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("Product found successfully", 200, product));
    }
}
