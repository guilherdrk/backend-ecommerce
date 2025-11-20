package com.guilherdrk.ecommerce.repository;

import com.guilherdrk.ecommerce.entities.BillingAddresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddresEntity, Long> {

}
