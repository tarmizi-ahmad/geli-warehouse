package com.geli.warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {

    @NotBlank(message = "Item name is required")
    private String name;

    private String description;

}