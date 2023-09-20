package com.wallet.wallet_msir_jour_groupe2.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldErrorTest {
    FieldError fieldError =new FieldError("Field","Error");;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getField() {
        String field="Field";
        assertEquals(field,fieldError.getField());
    }

    @Test
    void getErrorCode() {
        String error="Error";
        assertEquals(error,fieldError.getErrorCode());
    }

    @Test
    void setField() {
        String field="MYFIELD";
        fieldError.setField(field);
        assertNotEquals("Field",fieldError.getField());
    }

    @Test
    void setErrorCode() {
        String errorcode="MYERROR";
        fieldError.setErrorCode(errorcode);
        assertNotEquals("Error",fieldError.getErrorCode());
    }
}