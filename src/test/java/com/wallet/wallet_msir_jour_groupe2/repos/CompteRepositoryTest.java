package com.wallet.wallet_msir_jour_groupe2.repos;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CompteRepositoryTest {
    @Autowired
    CompteRepository compteRepository;

    @BeforeEach
    void setUp() {
        Compte compte=new Compte(5000.0, TypeStatutCompte.ACTIF);
        compteRepository.save(compte);
    }

    @AfterEach
    void tearDown() {
        compteRepository.deleteAll();
    }
}