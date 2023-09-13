package com.wallet.wallet_msir_jour_groupe2.model;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private Double montantTransaction;

    private TypeTransaction typeTransaction;

    private TypeStatutTransaction statutTransaction;

    private Long idReciver;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime dateTransaction;

    private Long user;

    private Long compte;

}
