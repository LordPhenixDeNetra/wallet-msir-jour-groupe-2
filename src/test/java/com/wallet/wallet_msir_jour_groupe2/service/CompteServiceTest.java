package com.wallet.wallet_msir_jour_groupe2.service;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.CompteDTO;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.TransactionRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.UserRepository;
import com.wallet.wallet_msir_jour_groupe2.util.NotFoundException;
import com.wallet.wallet_msir_jour_groupe2.util.WebUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CompteServiceTest {

    @Mock
    private CompteRepository compteRepository;

    @InjectMocks
    private CompteService compteService;
   @Mock
    private UserRepository userRepository;
    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        compteService=new CompteService(compteRepository,userRepository,transactionRepository);
    }

    /*
    @Test
    void testFindAll() {
        // Mocking the repository to return a list of Compte objects
        List<Compte> compteList = Arrays.asList(
//                new Compte(1L, 100.0, "Actif"),
//                new Compte(2L, 200.0, "Inactif")

                new Compte(),
                new Compte()
        );
        when(compteRepository.findAll()).thenReturn(compteList);

        List<CompteDTO> compteDTOList = compteService.findAll();

        assertEquals(2, compteDTOList.size());
        assertEquals(1L, compteDTOList.get(0).getId());
        assertEquals(2L, compteDTOList.get(1).getId());
    }

     */
    @Test
    void testFindAll() {
        List<Compte> initSimulateCompte=List.of(
                new Compte(1000.0,TypeStatutCompte.ACTIF),
                new Compte(200.0,TypeStatutCompte.ACTIF));
        when(compteRepository.findAll()).thenReturn(initSimulateCompte);
        List<CompteDTO> allCompte=compteService.findAll();
        assertEquals(initSimulateCompte.size(),allCompte.size());
    }
    @Test
    void testupdat()
    {
        Compte compte=new Compte(50000.0,TypeStatutCompte.ACTIF);
        when(compteRepository.findById(compte.getId())).thenReturn(Optional.of(compte));
        CompteDTO compteDTO=compteService.get(compte.getId());
        compteService.update(compte.getId(),compteDTO);
    }
    @Test
    void testGetExistingCompte() {
        Long accountId = 1L;
//        Compte existingCompte = new Compte(accountId, 100.0, "ACTIF");
        Compte existingCompte = new Compte();
        when(compteRepository.findById(accountId)).thenReturn(Optional.of(existingCompte));

        CompteDTO compteDTO = compteService.get(accountId);

        assertNotNull(compteDTO);
//        assertEquals(accountId, compteDTO.getId());
    }

    @Test
    void testGetNonExistingCompte() {
        Long nonExistingAccountId = 999L;
        when(compteRepository.findById(nonExistingAccountId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            compteService.get(nonExistingAccountId);
        });
    }

    @Test
    void testCreateCompte() {
        CompteDTO newCompteDTO = new CompteDTO();
        newCompteDTO.setSoldeCompte(300.0);
        newCompteDTO.setStatutCompte(TypeStatutCompte.valueOf("ACTIF"));

        when(compteRepository.save(any(Compte.class))).thenAnswer(invocation -> {
            Compte savedCompte = invocation.getArgument(0);
            savedCompte.setId(3L); // Setting an ID for the saved Compte
            return savedCompte;
        });

        Long createdAccountId = compteService.create(newCompteDTO);

        assertNotNull(createdAccountId);
        assertEquals(3L, createdAccountId);
    }

    /*
    @Test
    void testUpdateExistingCompte() {
        Long accountId = 1L;
//        Compte existingCompte = new Compte(accountId, 100.0, TypeStatutCompte.INACTIF);
        Compte existingCompte = new Compte();
        CompteDTO updatedCompteDTO = new CompteDTO();
        updatedCompteDTO.setSoldeCompte(200.0);
        updatedCompteDTO.setStatutCompte(TypeStatutCompte.valueOf("INACTIF"));

        when(compteRepository.findById(accountId)).thenReturn(Optional.of(existingCompte));
        when(compteRepository.save(any(Compte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> {
            compteService.update(accountId, updatedCompteDTO);
        });

        assertEquals(200.0, existingCompte.getSoldeCompte());
        assertEquals(TypeStatutCompte.INACTIF, existingCompte.getStatutCompte());
    }

     */

    @Test
    void testUpdateNonExistingCompte() {
        Long nonExistingAccountId = 999L;
        CompteDTO updatedCompteDTO = new CompteDTO();
        updatedCompteDTO.setSoldeCompte(200.0);
        updatedCompteDTO.setStatutCompte(TypeStatutCompte.valueOf("INACTIF"));

        when(compteRepository.findById(nonExistingAccountId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            compteService.update(nonExistingAccountId, updatedCompteDTO);
        });
    }

    @Test
    void testDeleteCompte() {
        Long accountId = 1L;

        assertDoesNotThrow(() -> {
            compteService.delete(accountId);
        });

        verify(compteRepository, times(1)).deleteById(accountId);
    }
    @Test
    void getReferencedWarning()
    {
        Long idCompte=3L;
        Long idUser=5L;
        Compte compte=new Compte();
        compte.setId(idCompte);
        User user=new User();
        user.setId(idUser);
        when(compteRepository.findById(idCompte)).thenReturn(Optional.of(compte));
        when(userRepository.findFirstByCompte(compte)).thenReturn(user);
        String warning=compteService.getReferencedWarning(idCompte);
        // Vérifier que le message d'avertissement est correct
        assertEquals(WebUtils.getMessage("compte.user.compte.referenced", user.getId()), warning);

    }

}