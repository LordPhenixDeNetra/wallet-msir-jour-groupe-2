package com.wallet.wallet_msir_jour_groupe2.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorResponse {

    private Integer httpStatus;
    private String exception;
    private String message;
    private List<FieldError> fieldErrors;
    public ErrorResponse(Integer httpStatus,String exception,String message, List<FieldError> fieldErrors)
    {
        this.httpStatus=httpStatus;
        this.exception=exception;
        this.message=message;
        this.fieldErrors=fieldErrors;
    }
    public ErrorResponse()
    {

    }

}
