package com.wallet.wallet_msir_jour_groupe2.service;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.Transaction;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.*;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.TransactionRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceTest {
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    TransactionService transactionService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompteRepository compteRepository;
    Compte compte;
    User user;
    @BeforeEach
    void setUp()
    {
        compte=new Compte(20000.0, TypeStatutCompte.ACTIF);
        user=new User("SENE","Mamadou", TypeUser.CUSTOMER,"778340335",compte);
        compteRepository.save(compte);
        userRepository.save(user);
    }

    @Test
    void findAll() {
        List<Transaction> initlistTransaction= List.of(
                new Transaction(1000.0, TypeTransaction.DEPOT, TypeStatutTransaction.REUSSITE,user.getId(),user,compte),
                new Transaction(2000.0, TypeTransaction.RETRAIT, TypeStatutTransaction.ENCOUR,user.getId(),user,compte)
        );
        when(transactionRepository.findAll()).thenReturn(initlistTransaction);
        List<TransactionDTO> listtdo= transactionService.findAll();
        assertEquals(initlistTransaction.size(),listtdo.size());
    }

    @Test
    void get() {
        Transaction t=new Transaction(1000.0, TypeTransaction.DEPOT, TypeStatutTransaction.REUSSITE,user.getId(),user,compte);
        transactionRepository.save(t);
        Optional<Transaction> t2= Optional.of(new Transaction(1000.0, TypeTransaction.RETRAIT, TypeStatutTransaction.ANNULER,user.getId(),user,compte));
        when(transactionRepository.findById(t.getId())).thenReturn(t2);
        TransactionDTO transactionDTO=transactionService.get(t.getId());
        assertEquals(t.getMontantTransaction(),transactionDTO.getMontantTransaction());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
    @AfterEach
    void tearDown()
    {
        userRepository.deleteAll();
        compteRepository.deleteAll();

    }
}