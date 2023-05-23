package eshopify.eshopifybackend.Model;

import org.springframework.http.HttpStatus;

public class ResponseDTO<T> {
    private HttpStatus statusCode;
    private String message;
    private  T payload;

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
