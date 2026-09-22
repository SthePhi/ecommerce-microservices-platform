package za.co.natus.product_service.exception;

public class DuplicateSkuException extends RuntimeException {

    private final String sku;

    public DuplicateSkuException(String sku) {
        super("SKU already exists: " + sku);
        this.sku = sku;
    }

    public String getSku() { return sku; }
}
