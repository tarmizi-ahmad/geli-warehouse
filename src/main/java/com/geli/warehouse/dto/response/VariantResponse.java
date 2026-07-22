package com.geli.warehouse.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class VariantResponse {

    private Long id;

    private String sku;

    private String color;

    private String size;

    private BigDecimal price;

    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}