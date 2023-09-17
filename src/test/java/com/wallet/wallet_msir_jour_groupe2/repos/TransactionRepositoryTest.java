package com.wallet.wallet_msir_jour_groupe2.repos;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.Transaction;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutTransaction;
import com.wallet.wallet_msir_jour_groupe2.model.TypeTransaction;
import com.wallet.wallet_msir_jour_groupe2.model.TypeUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private UserRepository userRepository;
    Compte compte=new Compte(20000.0, TypeStatutCompte.ACTIF);
    User firstUser=new User("SENE","Mamadou", TypeUser.CUSTOMER,"778340335",compte);


    @BeforeEach
    void setUp() {
        compteRepository.save(compte);
        userRepository.save(firstUser);
        Transaction t1=new Transaction(1000.0, TypeTransaction.DEPOT, TypeStatutTransaction.REUSSITE,firstUser.getId(),firstUser,compte);
        Transaction t2=new Transaction(2000.0, TypeTransaction.RETRAIT, TypeStatutTransaction.ENCOUR,firstUser.getId(),firstUser,compte);
        Transaction t3=new Transaction(3000.0, TypeTransaction.TRANSFERT, TypeStatutTransaction.ANNULER,firstUser.getId(),firstUser,compte);


        transactionRepository.save(t1);
        transactionRepository.save(t2);
        transactionRepository.save(t3);
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
    }

    @Test
    void findFirstByUser() {
        Transaction transaction=transactionRepository.findFirstByUser(firstUser);
        assertNotNull(transaction);
    }

    @Test
    void findFirstByCompte() {
        Transaction transactionbyCompte=transactionRepository.findFirstByCompte(compte);
        assertNotNull(transactionbyCompte);

    }

    /*@Test
    void testFindAllTransaction() {
        List<Transaction> alltransaction=transactionRepository.findAllTransaction();
        assertEquals(3,alltransaction.size());

    }*/
}