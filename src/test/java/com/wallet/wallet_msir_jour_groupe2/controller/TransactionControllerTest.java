package com.wallet.wallet_msir_jour_groupe2.controller;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.TransactionDTO;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutTransaction;
import com.wallet.wallet_msir_jour_groupe2.model.TypeTransaction;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.UserRepository;
import com.wallet.wallet_msir_jour_groupe2.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {
    @Mock
    private TransactionService transactionService;
    @InjectMocks
    private TransactionController transactionController;
    @Mock
    private TransactionDTO transactionDTO;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CompteRepository compteRepository;
    @Mock
    private Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void prepareContext() {
/*
        transactionController.prepareContext(model);

        // Verify that the model attributes "typeUserValues" and "compteValues" are set
        verify(model, times(1)).addAttribute(eq("typeTransactionValues"), any());
        verify(model, times(1)).addAttribute(eq("statutTransactionValues"), any());
        */
        //CompteRepository compteRepository=Mockito.mock(CompteRepository.class);
        //UserRepository userRepository=Mockito.mock(UserRepository.class);
        List<Compte> comptes=new ArrayList<>();
        comptes.add(new Compte());
        List<User> users=new ArrayList<>();
        users.add(new User());
        when(compteRepository.findAll()).thenReturn(comptes);
        when(userRepository.findAll()).thenReturn(users);
        //L'appel de la methode prepareContext, à tester
        transactionController.prepareContext(model);
        //Vérifier que les attributs ont été ajoutés au modèle comme prévu
        verify(model).addAttribute("typeTransactionValues", TypeTransaction.values());
        verify(model).addAttribute("statutTransactionValues", TypeStatutTransaction.values());
        /*verify(model).addAttribute("userValues",userRepository.findAll());
        verify(model).addAttribute("compteValues",compteRepository.findAll());*/
    }

    @Test
    void list() {
        List<TransactionDTO> simulateTDTO=new ArrayList<>();
        Mockito.when(transactionService.findAll()).thenReturn(simulateTDTO);
        String returned_view=transactionController.list(model);
        assertEquals("transaction/list",returned_view);
    }

    @Test
    void add() {
        String return_view=transactionController.add(transactionDTO);
       assertEquals("transaction/add",return_view);
    }

   @Test
    void testAdd() {
        BindingResult bindingResult=Mockito.mock(BindingResult.class);
        RedirectAttributes redirectAttributes=Mockito.mock(RedirectAttributes.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(transactionService.create(transactionDTO)).thenReturn(100L);
        String retur_viem=transactionController.add(transactionDTO);
        assertEquals("transaction/add",retur_viem);

    }

    @Test
    void edit() {
        long idTransaction=3L;
        String returned=transactionController.edit(idTransaction,model);
        Mockito.verify(transactionService).get(Mockito.any(long.class));
        assertEquals("transaction/edit",returned);
    }


    @Test
    void testEdit() {
        Long idTransaction=10L;
        BindingResult bindingResult=Mockito.mock(BindingResult.class);
        RedirectAttributes redirectAttributes=Mockito.mock(RedirectAttributes.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        String view=transactionController.edit(idTransaction,transactionDTO,bindingResult,redirectAttributes);
        assertEquals("redirect:/transactions",view);
    }

    @Test
    void delete() {
        Long id_transaction=100L;
        RedirectAttributes redirectAttributes=Mockito.mock(RedirectAttributes.class);
        String view=transactionController.delete(id_transaction,redirectAttributes);
        assertEquals("redirect:/transactions",view);
    }


}