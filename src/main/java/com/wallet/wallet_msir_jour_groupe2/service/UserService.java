package com.wallet.wallet_msir_jour_groupe2.service;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.Transaction;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.model.UserDTO;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.TransactionRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.UserRepository;
import com.wallet.wallet_msir_jour_groupe2.util.NotFoundException;
import com.wallet.wallet_msir_jour_groupe2.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;

    public UserService(final UserRepository userRepository, final CompteRepository compteRepository,
            final TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.compteRepository = compteRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    /*
    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }
    */

    public Long create(final UserDTO userDTO) {
        // Créez d'un compte avant l'utilisateur à qui il appartient
        final Compte compte = new Compte();
        compte.setSoldeCompte(0.0);
        compte.setStatutCompte(TypeStatutCompte.ACTIF);
        final Compte savedCompte = compteRepository.save(compte);

        // Création de l'utilisateur
        final User user = new User();
        mapToEntity(userDTO, user);

        // Liez le compte créé à l'utilisateur
        user.setCompte(savedCompte);

        // Enregistrez l'utilisateur
        final User savedUser = userRepository.save(user);
        return savedUser.getId();
    }



    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setNomUser(user.getNomUser());
        userDTO.setPrenomUser(user.getPrenomUser());
        userDTO.setTypeUser(user.getTypeUser());
        userDTO.setTelUser(user.getTelUser());
        userDTO.setPasswordUser(user.getPasswordUser());
        userDTO.setAvatarUser(user.getAvatarUser());
        userDTO.setCinUser(user.getCinUser());
        userDTO.setAdresseUser(user.getAdresseUser());
        userDTO.setCompte(user.getCompte() == null ? null : user.getCompte().getId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setNomUser(userDTO.getNomUser());
        user.setPrenomUser(userDTO.getPrenomUser());
        user.setTypeUser(userDTO.getTypeUser());
        user.setTelUser(userDTO.getTelUser());
        user.setPasswordUser(userDTO.getPasswordUser());
        user.setAvatarUser(userDTO.getAvatarUser());
        user.setCinUser(userDTO.getCinUser());
        user.setAdresseUser(userDTO.getAdresseUser());
        final Compte compte = userDTO.getCompte() == null ? null : compteRepository.findById(userDTO.getCompte())
                .orElseThrow(() -> new NotFoundException("compte not found"));
        user.setCompte(compte);
        return user;
    }

    public boolean compteExists(final Long id) {
        return userRepository.existsByCompteId(id);
    }

    public String getReferencedWarning(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Transaction userTransaction = transactionRepository.findFirstByUser(user);
        if (userTransaction != null) {
            return WebUtils.getMessage("user.transaction.user.referenced", userTransaction.getId());
        }
        return null;
    }

}
