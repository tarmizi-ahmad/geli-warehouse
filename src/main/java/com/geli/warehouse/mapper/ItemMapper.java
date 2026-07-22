package com.geli.warehouse.mapper;

import com.geli.warehouse.dto.response.ItemResponse;
import com.geli.warehouse.entity.Item;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemMapper {

    private final VariantMapper variantMapper;

    public ItemMapper(VariantMapper variantMapper) {
        this.variantMapper = variantMapper;
    }

    public ItemResponse toResponse(Item item) {

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
                            .map(variantMapper::toResponse)
                            .toList()
            );
        }

        return response;
    }
}