package za.co.natus.product_service.dto;

import java.util.List;

public class ErrorResponse {

    private String code;
    private List<String> messages;

    public ErrorResponse(String code, List<String> messages) {
        this.code = code;
        this.messages = messages;
    }

    public String getCode() { return code; }
    public List<String> getMessages() { return messages; }
}