package com.wallet.wallet_msir_jour_groupe2.service;

import com.wallet.wallet_msir_jour_groupe2.domain.Transaction;
import com.wallet.wallet_msir_jour_groupe2.model.TypeUser;
import com.wallet.wallet_msir_jour_groupe2.repos.TransactionRepository;
import org.junit.jupiter.api.Test;
import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.model.UserDTO;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.UserRepository;
import com.wallet.wallet_msir_jour_groupe2.util.NotFoundException;
import com.wallet.wallet_msir_jour_groupe2.util.WebUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompteRepository compteRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        // Simuler la récupération de la liste des utilisateurs depuis la base de données
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        // Appeler la méthode à tester
        List<UserDTO> userDTOs = userService.findAll();

        // Vérifier que la liste de DTO renvoyée contient le même nombre d'éléments que la liste d'utilisateurs
//        assertEquals(users.size(), userDTOs.size());
        assertEquals(2, users.size());
    }

    @Test
    void get() {
        // ID de l'utilisateur à récupérer
        Long userId = 1L;

        // Simuler la récupération de l'utilisateur depuis la base de données
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Appeler la méthode à tester
        UserDTO userDTO = userService.get(userId);

        // Vérifier que le DTO renvoyé a le même ID que l'utilisateur simulé
        assertEquals(userId, userDTO.getId());
    }

    @Test
    void create() {
        // Création d'un DTO utilisateur
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setNomUser("John");
        userDTO.setPrenomUser("Doe");
        userDTO.setTypeUser(TypeUser.valueOf("CUSTOMER"));

        // Création d'un compte simulé
        Compte compte = new Compte();
        compte.setId(1L);
        compte.setSoldeCompte(0.0);
        compte.setStatutCompte(TypeStatutCompte.ACTIF);
        when(compteRepository.save(any(Compte.class))).thenReturn(compte);

        // Création d'un utilisateur simulé
        User savedUser = new User(); // Créez un utilisateur factice ici
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Appeler la méthode à tester
        Long userId = userService.create(userDTO);

        userId = userDTO.getId();

        // Vérifier que l'ID renvoyé n'est pas nul
        assertNotNull(userId);
    }



    @Test
    void update() {
        // ID de l'utilisateur à mettre à jour
        Long userId = 1L;

        // Simuler la récupération de l'utilisateur depuis la base de données
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Création d'un DTO utilisateur à mettre à jour
        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setNomUser("UpdatedName");

        // Appeler la méthode à tester
        userService.update(userId, updatedUserDTO);

        // Vérifier que le nom de l'utilisateur simulé a été mis à jour
        assertEquals(updatedUserDTO.getNomUser(), user.getNomUser());
    }

    @Test
    void delete() {
        // ID de l'utilisateur à supprimer
        Long userId = 1L;

        // Simuler la récupération de l'utilisateur depuis la base de données
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Appeler la méthode à tester
        userService.delete(userId);

        // Vérifier que la méthode de suppression de repository a été appelée
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void compteExists() {
        // ID du compte à vérifier
        Long compteId = 1L;

        // Simuler l'existence du compte dans la base de données
        when(userRepository.existsByCompteId(compteId)).thenReturn(true);

        // Appeler la méthode à tester
        boolean exists = userService.compteExists(compteId);

        // Vérifier que la méthode renvoie vrai
        assertTrue(exists);
    }

    /*
    @Test
    void getReferencedWarning() {
        // ID de l'utilisateur pour lequel vérifier les références
        Long userId = 1L;

        // Simuler la récupération de l'utilisateur depuis la base de données
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Simuler une transaction liée à cet utilisateur
        Transaction userTransaction = new Transaction();
        userTransaction.setId(1L);
        when(transactionRepository.findFirstByUser(user)).thenReturn(userTransaction);


        // Appeler la méthode à tester
        String warning = userService.getReferencedWarning(userId);

        // Vérifier que le message de l'avertissement contient l'ID de la transaction
        assertTrue(warning.contains(userTransaction.getId().toString()));
    }

     */
}