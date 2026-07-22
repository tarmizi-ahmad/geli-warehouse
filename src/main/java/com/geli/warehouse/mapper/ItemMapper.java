package com.geli.warehouse.mapper;

import com.geli.warehouse.dto.response.ItemResponse;
import com.geli.warehouse.entity.Item;

import java.util.stream.Collectors;

public final class ItemMapper {

    private ItemMapper() {
    }

    public static ItemResponse toResponse(Item item) {

        ItemResponse response = new ItemResponse();

        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setCreatedAt(item.getCreatedAt());
        response.setUpdatedAt(item.getUpdatedAt());

        if (item.getVariants() != null) {
            response.setVariants(
                    item.getVariants()
                            .stream()
                            .map(VariantMapper::toResponse)
                            .collect(Collectors.toList())
            );
        }

        return response;
    }

}