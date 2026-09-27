package za.co.natus.product_service.service;

import org.jspecify.annotations.Nullable;
import za.co.natus.product_service.dto.CreateProductRequest;
import za.co.natus.product_service.dto.ProductResponse;

public interface ProductService {
    @Nullable Void createProduct(CreateProductRequest productRequest);

    @Nullable ProductResponse getProduct(Long id);

    void updateProduct(CreateProductRequest productRequest);

    void deleteProduct(Long id);
}
