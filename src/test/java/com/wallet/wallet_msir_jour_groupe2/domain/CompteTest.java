package com.wallet.wallet_msir_jour_groupe2.domain;

import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CompteTest {
    @Autowired
    private CompteRepository compteRepository;
    Compte compte;

    @BeforeEach
    void setUp() {
        compte=new Compte(2000.0, TypeStatutCompte.ACTIF);
        compteRepository.save(compte);
    }

    @Test
    void getId() {
        Optional<Compte> c=compteRepository.findById(compte.getId());
        assertNotNull(c);
        assertEquals(compte.getId(),c.get().getId());
    }

    @Test
    void getSoldeCompte() {
       Optional<Compte> c=compteRepository.findById(compte.getId());
       double solde=c.get().getSoldeCompte();
       assertEquals(compte.getSoldeCompte(),c.get().getSoldeCompte());
    }

    @Test
    void getStatutCompte() {
        Optional<Compte> current_compte=compteRepository.findById(compte.getId());
        assertNotNull(current_compte);
        assertEquals(TypeStatutCompte.ACTIF,current_compte.get().getStatutCompte());
    }

    @Test
    void getTransactions() {
        Optional<Compte> c=compteRepository.findById(compte.getId());
        Set<Transaction> list=c.get().getTransactions();
        assertNull(list);
    }

    @Test
    void getDateCreated() {
        Optional<Compte> c=compteRepository.findById(compte.getId());
        OffsetDateTime datecreated=c.get().getDateCreated();
        assertNotEquals(OffsetDateTime.now(),datecreated);
    }

    @Test
    void getLastUpdated() {
        Optional<Compte> c=compteRepository.findById(compte.getId());
        OffsetDateTime lastupdate=c.get().getLastUpdated();
        assertNotEquals(OffsetDateTime.now(),lastupdate);
    }

    @Test
    void setId() {
        Optional<Compte> c=compteRepository.findById(compte.getId());
        //here we update id of our compte and we do test for new. the actual id of compte is different to the previous
        compte.setId(10001L);
        assertNotEquals(1000L,compte.getId());

    }

    @Test
    void setSoldeCompte() {
        //change the solde of current compte and test if compte has always the initial solde
        Double solde=compte.getSoldeCompte();
        compte.setSoldeCompte(0.0);
        assertNotEquals(compte.getSoldeCompte(),solde);
    }

    @Test
    void setStatutCompte() {
        // do not actif compte and do test
        compte.setStatutCompte(TypeStatutCompte.INACTIF);
        assertNotEquals(TypeStatutCompte.ACTIF,compte.getStatutCompte());
    }

    @Test
    void setTransactions() {
        //do two new transactions for the current compte and test if list of transactions isn't null
        Set<Transaction> listTransaction= Set.of(new Transaction(),new Transaction());
        compte.setTransactions(listTransaction);
        assertEquals(2,compte.getTransactions().size());
    }

    @Test
    void setDateCreated() {
        compte.setDateCreated(OffsetDateTime.parse("2023-05-19T15:54:14.214Z"));
        assertNotEquals(compte.getDateCreated(),OffsetDateTime.now());
    }

    @Test
    void setLastUpdated() {
        compte.setLastUpdated(OffsetDateTime.MIN);
        assertNotEquals(compte.getDateCreated(),OffsetDateTime.now());
    }
    @BeforeEach
    void tearDown()
    {
        compteRepository.deleteAll();
        compte = null;
    }
}