package com.wallet.wallet_msir_jour_groupe2.repos;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompteRepository extends JpaRepository<Compte, Long> {
}
