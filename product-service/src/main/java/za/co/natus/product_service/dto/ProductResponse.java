package za.co.natus.product_service.dto;


import za.co.natus.product_service.entity.Product;

import java.math.BigDecimal;

public class ProductResponse {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private Integer quantity;

    public static ProductResponse from(Product p) {
        ProductResponse r = new ProductResponse();
        r.id = p.getId();
        r.sku = p.getSku();
        r.name = p.getName();
        r.description = p.getDescription();
        r.category = p.getCategory();
        r.price = p.getPrice();
        r.quantity = p.getQuantity();
        return r;
    }

    public Long getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public Integer getQuantity() { return quantity; }
}