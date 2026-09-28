package za.co.natus.product_service.mapper;

import za.co.natus.product_service.dto.CreateProductRequest;
import za.co.natus.product_service.entity.Product;

public class ProductMapper {
    public static Product to(CreateProductRequest productRequest) {
        return Product.builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .category(productRequest.getCategory())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
    }
}
