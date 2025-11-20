package com.guilherdrk.ecommerce.dto;

import com.guilherdrk.ecommerce.entities.BillingAddresEntity;

public record CreateUserDTO(String fullName,
                            String address,
                            String number,
                            String complement) {
}
