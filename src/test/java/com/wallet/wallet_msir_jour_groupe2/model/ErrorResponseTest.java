package com.wallet.wallet_msir_jour_groupe2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {
    private List<FieldError> fieldErrorList=List.of(new FieldError(),new FieldError());
    private ErrorResponse errorResponse=new ErrorResponse();

    @BeforeEach
    void setUp() {
        errorResponse=new ErrorResponse(200,"no exception","request ok",fieldErrorList);
    }

    @Test
    void getHttpStatus() {
        assertEquals(200,errorResponse.getHttpStatus());
    }

    @Test
    void getException() {
        String exception="no exception";
        assertEquals(exception,errorResponse.getException());
    }

    @Test
    void getMessage() {
        String message="request ok";
        assertEquals(message,errorResponse.getMessage());
    }

    @Test
    void getFieldErrors() {
        List<FieldError> list=List.of(new FieldError(),new FieldError());
        assertEquals(list.size(),errorResponse.getFieldErrors().size());
    }

    @Test
    void setHttpStatus() {
        errorResponse.setHttpStatus(500);
        assertNotEquals(200,errorResponse.getHttpStatus());
    }

    @Test
    void setException() {
        errorResponse.setException("RuntimeException");
        assertNotEquals("no exception",errorResponse.getException());
    }

    @Test
    void setMessage() {
        errorResponse.setMessage("error server");
        assertNotEquals("request ok",errorResponse.getMessage());
    }

    @Test
    void setFieldErrors() {
        errorResponse.setFieldErrors(List.of(new FieldError()));
        assertNotEquals(2,errorResponse.getFieldErrors().size());
    }
}