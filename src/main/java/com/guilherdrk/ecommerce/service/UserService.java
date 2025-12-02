package com.guilherdrk.ecommerce.service;

import com.guilherdrk.ecommerce.dto.CreateUserDTO;
import com.guilherdrk.ecommerce.entities.BillingAddresEntity;
import com.guilherdrk.ecommerce.entities.UserEntity;
import com.guilherdrk.ecommerce.repository.BillingAddressRepository;
import com.guilherdrk.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UserEntity createUser(CreateUserDTO dto){
        var billingAddress = new BillingAddresEntity();
        billingAddress.setAddress(dto.address());
        billingAddress.setNumber(dto.number());
        billingAddress.setComplement(dto.complement());
        var saveBillingAddress = billingAddressRepository.save(billingAddress);

        var entity = new UserEntity();
        entity.setFullName(dto.fullName());
        entity.setBillingAddress(saveBillingAddress);

        return userRepository.save(entity);
    }

    public Optional<UserEntity> findById(UUID userId) {
        return userRepository.findById(userId);
    }
}
