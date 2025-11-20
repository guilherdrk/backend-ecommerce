package com.guilherdrk.ecommerce.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne
    @JoinColumn(name = "billing_address_id")
    private BillingAddresEntity billingAddress;

    public UserEntity() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BillingAddresEntity getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddresEntity billingAddress) {
        this.billingAddress = billingAddress;
    }
}
