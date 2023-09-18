package com.wallet.wallet_msir_jour_groupe2.domain;

import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutTransaction;
import com.wallet.wallet_msir_jour_groupe2.model.TypeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    Transaction t;
    TypeTransaction typeTransaction;
    TypeStatutTransaction statutTransaction;
    Compte compte;


    @BeforeEach
    void setUp() {
        t=new Transaction();
        typeTransaction=TypeTransaction.DEPOT;
        statutTransaction=TypeStatutTransaction.REUSSITE;
        compte=new Compte();
        t.setId(1L);
        t.setMontantTransaction(1000.0);
    }

    @Test
    void getId() {
        assertEquals(1,t.getId());

    }

    @Test
    void getMontantTransaction() {
        assertNotNull(t);
        assertEquals(1000.0, t.getMontantTransaction());
    }

    @Test
    void getTypeTransaction() {
        t.setTypeTransaction(typeTransaction);
        assertNotNull(typeTransaction);
        assertEquals(TypeTransaction.DEPOT,t.getTypeTransaction());
    }

    @Test
    void getStatutTransaction() {
        ArrayList<Transaction> my_transaction=new ArrayList<Transaction>();
        my_transaction.add(new Transaction());
        my_transaction.add(t);
        t.setStatutTransaction(statutTransaction);
       assertFalse(my_transaction.size()==0);
       assertEquals(TypeStatutTransaction.REUSSITE,my_transaction.get(1).getStatutTransaction());
    }

    @Test
    void getIdReciver() {
        t.setIdReciver(2L);
        assertTrue(2L==t.getIdReciver());
    }

    @Test
    void getDateTransaction() {
        t.setDateTransaction(OffsetDateTime.now());
        assertNotSame(OffsetDateTime.now(),t.getDateTransaction());
    }

    @Test
    void getUser() {
        User user=new User();
        user.setNomUser("Mamadou");
        t.setUser(user);
        assertNotNull(t.getUser().getNomUser());
    }

    @Test
    void getCompte() {
        t.setCompte(compte);
        assertTrue(compte.equals(t.getCompte()));

    }

    @Test
    void getDateCreated() {
        OffsetDateTime current_date= OffsetDateTime.now();
        t.setDateCreated(OffsetDateTime.now());
        assertTrue(current_date.equals(t.getDateCreated()));
    }

    @Test
    void getLastUpdated() {
        t.setLastUpdated(OffsetDateTime.now(ZoneOffset.UTC));
        OffsetDateTime last_update=OffsetDateTime.now();
        assertFalse(last_update.equals(t.getLastUpdated()));
    }

    @Test
    void setId() {
        t.setId(10000L);
        assertTrue(10000L==t.getId());
    }

    @Test
    void setMontantTransaction() {
        //update initial value of montant to 500.0 and verify condition
        t.setMontantTransaction(500.0);
        assertNotEquals(1000.0,t.getMontantTransaction());
    }

    @Test
    void setTypeTransaction() {
        //update transaction type now to do RETRAIT on do verification again
        t.setTypeTransaction(TypeTransaction.RETRAIT);
        assertNotEquals(TypeTransaction.DEPOT,t.getTypeTransaction());
    }

    @Test
    void setStatutTransaction() {
        //update transaction status to a new status
        t.setStatutTransaction(TypeStatutTransaction.ENCOUR);
        assertNotEquals(TypeStatutTransaction.REUSSITE,t.getStatutTransaction());
    }

    @Test
    void setIdReciver() {
        // change receveir for the transaction, thus add a new receiver
        t.setIdReciver(20000L);
        assertNotEquals(2L,t.getIdReciver());
    }

    @Test
    void setDateTransaction() {
        //change the date for the current transaction.
        t.setDateTransaction(OffsetDateTime.parse("2023-05-19T12:35:16.213510200Z"));
        assertNotEquals(OffsetDateTime.now(),t.getDateTransaction());
    }

    @Test
    void setUser() {
        //change the user who do the transaction.
        User user=new User();
        user.setNomUser("Moussa");
        t.setUser(user);
        assertNotEquals("Mamadou",t.getUser().getNomUser());
    }

    @Test
    void setCompte() {
        //modify transaction compte, and compare the previous transaction compte to new compte
        t.setCompte(new Compte());
        assertNotEquals(compte, t.getCompte());
    }

    @Test
    void setDateCreated() {
        //
        t.setDateCreated(OffsetDateTime.parse("2023-05-19T12:35:16.213510200Z"));
        assertFalse(OffsetDateTime.now()==t.getDateCreated());
    }

    @Test
    void setLastUpdated() {
    }
}