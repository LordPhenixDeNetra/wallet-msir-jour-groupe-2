package com.wallet.wallet_msir_jour_groupe2.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @Size(max = 60)
    private String nomUser;

    @Size(max = 120)
    private String prenomUser;

    private TypeUser typeUser;

    @Size(max = 25)
    private String telUser;

    @Size(max = 255)
    private String passwordUser;

    @Size(max = 255)
    private String avatarUser;

    @Size(max = 25)
    private String cinUser;

    @Size(max = 100)
    private String adresseUser;

    private Long compte;

}
