package com.wallet.wallet_msir_jour_groupe2.config;

import com.wallet.wallet_msir_jour_groupe2.model.ErrorResponse;
import com.wallet.wallet_msir_jour_groupe2.util.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class RestExceptionHandlerTest {
    private RestExceptionHandler restExceptionHandler;

    @BeforeEach
    void setUp() {
        restExceptionHandler=new RestExceptionHandler();
    }

    @Test
    void handleNotFound() {
        NotFoundException exception=new NotFoundException("NOT FOUND");
        ResponseEntity<ErrorResponse> response=restExceptionHandler.handleNotFound(exception);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals("NotFoundException", response.getBody().getException());
        assertEquals("NOT FOUND",response.getBody().getMessage());
    }

    @Test
    void handleMethodArgumentNotValid() {
        MethodArgumentNotValidException exception= Mockito.mock(MethodArgumentNotValidException.class);
        Mockito.when(exception.getBindingResult()).thenReturn(Mockito.mock(BindingResult.class));
        ResponseEntity<ErrorResponse> response=restExceptionHandler.handleMethodArgumentNotValid(exception);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("MethodArgumentNotValidException",response.getBody().getException());
    }

    @Test
    void handleResponseStatus() {
        ResponseStatusException exception=new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bad request");
        ResponseEntity<ErrorResponse> response=restExceptionHandler.handleResponseStatus(exception);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("ResponseStatusException",response.getBody().getException());
        assertEquals("400 BAD_REQUEST", response.getBody().getMessage().substring(0,15));
    }

    @Test
    void handleThrowable() {
        Throwable exception=Mockito.mock(Throwable.class);
        ResponseEntity<ErrorResponse> response=restExceptionHandler.handleThrowable(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
        assertEquals("Throwable",response.getBody().getException());
    }
}