package com.wallet.wallet_msir_jour_groupe2.service;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.Transaction;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.CompteDTO;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.TransactionRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.UserRepository;
import com.wallet.wallet_msir_jour_groupe2.util.NotFoundException;
import com.wallet.wallet_msir_jour_groupe2.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CompteService {

    private final CompteRepository compteRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public CompteService(final CompteRepository compteRepository,
            final UserRepository userRepository,
            final TransactionRepository transactionRepository) {
        this.compteRepository = compteRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<CompteDTO> findAll() {
        final List<Compte> comptes = compteRepository.findAll(Sort.by("id"));
        return comptes.stream()
                .map(compte -> mapToDTO(compte, new CompteDTO()))
                .toList();
    }

    public CompteDTO get(final Long id) {
        return compteRepository.findById(id)
                .map(compte -> mapToDTO(compte, new CompteDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CompteDTO compteDTO) {
        final Compte compte = new Compte();
        mapToEntity(compteDTO, compte);
        return compteRepository.save(compte).getId();
    }

    public void update(final Long id, final CompteDTO compteDTO) {
        final Compte compte = compteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(compteDTO, compte);
        compteRepository.save(compte);
    }

    public void delete(final Long id) {
        compteRepository.deleteById(id);
    }

    private CompteDTO mapToDTO(final Compte compte, final CompteDTO compteDTO) {
        compteDTO.setId(compte.getId());
        compteDTO.setSoldeCompte(compte.getSoldeCompte());
        compteDTO.setStatutCompte(compte.getStatutCompte());
        return compteDTO;
    }

    private Compte mapToEntity(final CompteDTO compteDTO, final Compte compte) {
        compte.setSoldeCompte(compteDTO.getSoldeCompte());
        compte.setStatutCompte(compteDTO.getStatutCompte());
        return compte;
    }

    public String getReferencedWarning(final Long id) {
        final Compte compte = compteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final User compteUser = userRepository.findFirstByCompte(compte);
        if (compteUser != null) {
            return WebUtils.getMessage("compte.user.compte.referenced", compteUser.getId());
        }
        final Transaction compteTransaction = transactionRepository.findFirstByCompte(compte);
        if (compteTransaction != null) {
            return WebUtils.getMessage("compte.transaction.compte.referenced", compteTransaction.getId());
        }
        return null;
    }

}
