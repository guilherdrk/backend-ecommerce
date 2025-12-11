package com.guilherdrk.ecommerce.dto;

import java.util.List;

public record ApiResponseDTO<T>(List<T> content,
                                PaginationResponseDTO paginationResponse) {
}
