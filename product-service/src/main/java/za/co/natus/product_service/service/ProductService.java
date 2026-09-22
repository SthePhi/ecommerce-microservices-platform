package za.co.natus.product_service.service;

import org.jspecify.annotations.Nullable;
import za.co.natus.product_service.dto.CreateProductRequest;

public interface ProductService {
    @Nullable Void createProduct(CreateProductRequest productRequest);
}
