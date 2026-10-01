package za.co.natus.product_service.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import za.co.natus.product_service.dto.CreateProductRequest;
import za.co.natus.product_service.dto.ProductResponse;
import za.co.natus.product_service.entity.Product;
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
    public void updateProduct(Long id, CreateProductRequest productRequest) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("The Product you want to update, with the ID:: " +id+ " does not exist."));
        if (product != null){
            product.setSku(productRequest.getSku());
            product.setName(productRequest.getName());
            product.setCategory(productRequest.getCategory());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
        }

        productRepository.save(ProductMapper.to(productRequest));
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(ProductResponse::from).toList();
    }
}
