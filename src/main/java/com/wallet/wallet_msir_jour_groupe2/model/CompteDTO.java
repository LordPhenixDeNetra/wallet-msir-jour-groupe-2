package com.wallet.wallet_msir_jour_groupe2.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CompteDTO {

    private Long id;
    private Double soldeCompte;
    private TypeStatutCompte statutCompte;

}
