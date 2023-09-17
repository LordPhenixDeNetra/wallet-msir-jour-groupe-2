package com.wallet.wallet_msir_jour_groupe2.repos;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.model.TypeUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
     CompteRepository compteRepository;

    Compte compte=new Compte(2000.0, TypeStatutCompte.ACTIF);

    @BeforeEach
    void setUp() {
        compteRepository.save(compte);
        User user1=new User("SENE","Momo", TypeUser.CUSTOMER,"778340335",compte);
        userRepository.save(user1);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void existsByCompteId() {
        Boolean user_exit=userRepository.existsByCompteId(compte.getId());
        assertTrue(user_exit);
        //

    }

    @Test
    void findFirstByCompte() {
        User user=userRepository.findFirstByCompte(compte);
        assertNotNull(user);
        assertEquals(user.getCompte(),compte);
    }
}