package za.co.natus.product_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.natus.product_service.dto.CreateProductRequest;
import za.co.natus.product_service.dto.ProductResponse;
import za.co.natus.product_service.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductRequest productRequest){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> retrieveProduct(@PathVariable Long id){
        return  ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> retrieveAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody CreateProductRequest productRequest){
        productService.updateProduct(productRequest);
        return null;
    }

//    Deactivate Product:Products should preferably be deactivated rather than physically deleted.
    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return null;
    }
}
