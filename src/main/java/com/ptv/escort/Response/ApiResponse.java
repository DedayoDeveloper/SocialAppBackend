package com.ptv.escort.Response;


import org.springframework.http.HttpStatus;

/**
 *
 * @author oreoluwa
 */
public class ApiResponse<T> {


    private T response;
    private HttpStatus status;
    private String message;
    private String error;
    private String responsecode;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }
}