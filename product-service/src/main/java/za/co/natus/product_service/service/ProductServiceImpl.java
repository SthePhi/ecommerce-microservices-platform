package za.co.natus.product_service.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.natus.product_service.dto.CreateProductRequest;
import za.co.natus.product_service.dto.ProductResponse;
import za.co.natus.product_service.mapper.ProductMapper;
import za.co.natus.product_service.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public @Nullable Void createProduct(CreateProductRequest productRequest) {
        productRepository.save(ProductMapper.to(productRequest));

        return null;
    }

    @Override
    public @Nullable ProductResponse getProduct(Long id) {
        return ProductResponse.from(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }

    @Override
    public void updateProduct(CreateProductRequest productRequest) {

    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(ProductResponse::from).toList();
    }
}
