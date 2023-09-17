package com.wallet.wallet_msir_jour_groupe2.domain;

import com.wallet.wallet_msir_jour_groupe2.model.TypeUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "\"user\"")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User {

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

    @Column(length = 60)
    private String nomUser;

    @Column(length = 120)
    private String prenomUser;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    @Column(length = 25)
    private String telUser;

    @Column
    private String passwordUser;

    @Column
    private String avatarUser;

    @Column(length = 25)
    private String cinUser;

    @Column(length = 100)
    private String adresseUser;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id", unique = true)
    private Compte compte;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public User( String nomUser, String prenomUser, TypeUser typeUser, String telUser, Compte compte) {
        this.nomUser=nomUser;
        this.prenomUser=prenomUser;
        this.typeUser=typeUser;
        this.telUser=telUser;
        this.compte=compte;
        this.dateCreated=OffsetDateTime.now();
        this.lastUpdated=OffsetDateTime.now();
    }

    public User() {

    }
}
