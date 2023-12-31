package com.wallet.wallet_msir_jour_groupe2.domain;

import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Compte {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private Double soldeCompte;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeStatutCompte statutCompte;

    /*@OneToOne(mappedBy = "compte", fetch = FetchType.LAZY)
    private User user;*/

    @OneToMany(mappedBy = "compte")
    private Set<Transaction> transactions;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Compte(double soldeCompte, TypeStatutCompte statusCompte) {
        this.soldeCompte=soldeCompte;
        this.statutCompte=statusCompte;
        //this.user= this.user;
        this.dateCreated=OffsetDateTime.now();
        this.lastUpdated=OffsetDateTime.now();
    }

    public Compte() {

    }
}
