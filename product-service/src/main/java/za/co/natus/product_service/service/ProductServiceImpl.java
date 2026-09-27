package za.co.natus.product_service.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import za.co.natus.product_service.dto.CreateProductRequest;
import za.co.natus.product_service.dto.ProductResponse;
import za.co.natus.product_service.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public @Nullable Void createProduct(CreateProductRequest productRequest) {


        return null;
    }

    @Override
    public @Nullable ProductResponse getProduct(Long id) {
        return null;
    }

    @Override
    public void updateProduct(CreateProductRequest productRequest) {

    }

    @Override
    public void deleteProduct(Long id) {

    }
}
