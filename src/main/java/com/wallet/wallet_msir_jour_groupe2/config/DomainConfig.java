package com.wallet.wallet_msir_jour_groupe2.config;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.wallet.wallet_msir_jour_groupe2.domain")
@EnableJpaRepositories("com.wallet.wallet_msir_jour_groupe2.repos")
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class DomainConfig {

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

}
