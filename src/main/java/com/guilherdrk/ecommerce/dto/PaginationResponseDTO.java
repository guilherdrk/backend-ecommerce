package com.guilherdrk.ecommerce.dto;

public record PaginationResponseDTO(Integer page,
                                    Integer pageSize,
                                    Long totalElements,
                                    Integer totalPages) {
}
