package com.wallet.wallet_msir_jour_groupe2.domain;

import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.model.TypeUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class UserTest {
    private User user;
    private Compte compte;


    @BeforeEach
    void setUp() {
        compte=new Compte(1000.0, TypeStatutCompte.ACTIF);
        user=new User("SENE","Mamadou", TypeUser.CUSTOMER,"778340335",compte);
    }

    @AfterEach
    void tearDown() {
        compte=null;
        user=null;
    }

    @Test
    void getNomUser() {
        String nom="SENE";
        assertEquals(user.getNomUser(),nom);
    }

    @Test
    void getPrenomUser() {
        String prenom="Mamadou";
        assertEquals(user.getPrenomUser(),prenom);
    }

    @Test
    void getTypeUser() {
        TypeUser type=TypeUser.ADMIN;
        assertNotEquals(user.getTypeUser(),type);
    }

    @Test
    void getTelUser() {
        String telephone="778340335";
        assertEquals(user.getTelUser(),telephone);
    }

    @Test
    void getTransactions() {
        Set<Transaction> alltransaction=user.getTransactions();
        assertNull(alltransaction);
    }

    @Test
    void getCompte() {
        Compte c=user.getCompte();
        assertSame(compte,c);
    }

    @Test
    void setId() {
    }

    @Test
    void setNomUser() {
        user.setNomUser("Bamba");
        assertNotEquals("SENE",user.getNomUser());
    }

    @Test
    void setPrenomUser() {
        user.setPrenomUser("Ahmadou Bamba");
        assertNotEquals("Mamadou",user.getNomUser());
    }

    @Test
    void setTypeUser() {
        TypeUser typeUser=TypeUser.ADMIN;
        user.setTypeUser(typeUser);
        assertEquals(TypeUser.ADMIN,user.getTypeUser());
    }

    @Test
    void setTelUser() {
        String telephone="760250900";
        user.setTelUser(telephone);
        assertNotEquals("778340335",user.getTelUser());
    }

    @Test
    void setTransactions() {
        //simulate two transaction for current user and do test
        Set<Transaction> listTransaction= Set.of(new Transaction(),new Transaction());
        user.setTransactions(listTransaction);
        assertEquals(2,user.getTransactions().size());
    }

    @Test
    void setCompte() {
        //simulate delete compte for user, then the current user hasn't compte
        Compte c=null;
        user.setCompte(c);
        assertNull(user.getCompte());

    }
}