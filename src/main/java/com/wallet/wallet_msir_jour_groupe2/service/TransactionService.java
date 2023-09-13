package com.wallet.wallet_msir_jour_groupe2.service;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.Transaction;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.TransactionDTO;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.TransactionRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.UserRepository;
import com.wallet.wallet_msir_jour_groupe2.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CompteRepository compteRepository;

    public TransactionService(final TransactionRepository transactionRepository,
            final UserRepository userRepository, final CompteRepository compteRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.compteRepository = compteRepository;
    }

    public List<TransactionDTO> findAll() {
        final List<Transaction> transactions = transactionRepository.findAll(Sort.by("id"));
        return transactions.stream()
                .map(transaction -> mapToDTO(transaction, new TransactionDTO()))
                .toList();
    }

    public TransactionDTO get(final Long id) {
        return transactionRepository.findById(id)
                .map(transaction -> mapToDTO(transaction, new TransactionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TransactionDTO transactionDTO) {
        final Transaction transaction = new Transaction();
        mapToEntity(transactionDTO, transaction);
        return transactionRepository.save(transaction).getId();
    }

    public void update(final Long id, final TransactionDTO transactionDTO) {
        final Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(transactionDTO, transaction);
        transactionRepository.save(transaction);
    }

    public void delete(final Long id) {
        transactionRepository.deleteById(id);
    }

    private TransactionDTO mapToDTO(final Transaction transaction,
            final TransactionDTO transactionDTO) {
        transactionDTO.setId(transaction.getId());
        transactionDTO.setMontantTransaction(transaction.getMontantTransaction());
        transactionDTO.setTypeTransaction(transaction.getTypeTransaction());
        transactionDTO.setStatutTransaction(transaction.getStatutTransaction());
        transactionDTO.setIdReciver(transaction.getIdReciver());
        transactionDTO.setDateTransaction(transaction.getDateTransaction());
        transactionDTO.setUser(transaction.getUser() == null ? null : transaction.getUser().getId());
        transactionDTO.setCompte(transaction.getCompte() == null ? null : transaction.getCompte().getId());
        return transactionDTO;
    }

    private Transaction mapToEntity(final TransactionDTO transactionDTO,
            final Transaction transaction) {
        transaction.setMontantTransaction(transactionDTO.getMontantTransaction());
        transaction.setTypeTransaction(transactionDTO.getTypeTransaction());
        transaction.setStatutTransaction(transactionDTO.getStatutTransaction());
        transaction.setIdReciver(transactionDTO.getIdReciver());
        transaction.setDateTransaction(transactionDTO.getDateTransaction());
        final User user = transactionDTO.getUser() == null ? null : userRepository.findById(transactionDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        transaction.setUser(user);
        final Compte compte = transactionDTO.getCompte() == null ? null : compteRepository.findById(transactionDTO.getCompte())
                .orElseThrow(() -> new NotFoundException("compte not found"));
        transaction.setCompte(compte);
        return transaction;
    }

}
