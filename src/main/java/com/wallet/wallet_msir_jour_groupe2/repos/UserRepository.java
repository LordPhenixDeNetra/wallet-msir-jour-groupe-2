package com.wallet.wallet_msir_jour_groupe2.repos;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCompteId(Long id);

    User findFirstByCompte(Compte compte);

}
