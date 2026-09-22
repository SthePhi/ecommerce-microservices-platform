package za.co.natus.product_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.natus.product_service.dto.CreateProductRequest;
import za.co.natus.product_service.dto.ProductResponse;
import za.co.natus.product_service.service.ProductService;

@RestController
@RequestMapping("api/vi/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    Create Product
    @PostMapping
    public ResponseEntity<> createProduct(@RequestBody CreateProductRequest productRequest){
        return ResponseEntity.accepted(productService.createProduct(productRequest));
    }

//    Retrieve Products
    @GetMapping
    public ResponseEntity<ProductResponse> retrieveProduct(@PathVariable Long id){
        return  ResponseEntity.;
    }

//    Update Product
    @PutMapping
    public ResponseEntity<Void> updateProduct(){
        return null;
    }

//    Deactivate Product:Products should preferably be deactivated rather than physically deleted.
    @PatchMapping
    public ResponseEntity<Void> deactivateProduct(){
        return null;
    }
}
