package com.geli.warehouse.mapper;

import com.geli.warehouse.dto.response.VariantResponse;
import com.geli.warehouse.entity.Variant;
import org.springframework.stereotype.Component;

@Component
public class VariantMapper {

    public VariantResponse toResponse(Variant variant) {

        VariantResponse response = new VariantResponse();

        response.setId(variant.getId());
        response.setSku(variant.getSku());
        response.setColor(variant.getColor());
        response.setSize(variant.getSize());
        response.setPrice(variant.getPrice());
        response.setStock(variant.getStock());
        response.setCreatedAt(variant.getCreatedAt());
        response.setUpdatedAt(variant.getUpdatedAt());

        return response;
    }
}