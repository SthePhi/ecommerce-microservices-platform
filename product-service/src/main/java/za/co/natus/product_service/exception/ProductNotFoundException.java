package za.co.natus.product_service.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String m){
        super(m);
    }

}
