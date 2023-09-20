package com.wallet.wallet_msir_jour_groupe2.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FieldError {

    private String field;
    private String errorCode;

    public FieldError(String field, String errorCode)
    {
        this.field=field;
        this.errorCode=errorCode;
    }
    public FieldError()
    {

    }
}
