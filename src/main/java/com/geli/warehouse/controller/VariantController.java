package com.geli.warehouse.controller;

import com.geli.warehouse.dto.request.SellRequest;
import com.geli.warehouse.dto.request.VariantRequest;
import com.geli.warehouse.dto.response.VariantResponse;
import com.geli.warehouse.service.VariantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VariantController {

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @PostMapping("/items/{itemId}/variants")
    @ResponseStatus(HttpStatus.CREATED)
    public VariantResponse create(
            @PathVariable Long itemId,
            @Valid @RequestBody VariantRequest request) {

        return variantService.create(itemId, request);
    }

    @GetMapping("/items/{itemId}/variants")
    public List<VariantResponse> findByItem(
            @PathVariable Long itemId) {

        return variantService.findByItem(itemId);
    }

    @GetMapping("/variants/{id}")
    public VariantResponse findById(@PathVariable Long id) {
        return variantService.findById(id);
    }

    @PutMapping("/variants/{id}")
    public VariantResponse update(
            @PathVariable Long id,
            @Valid @RequestBody VariantRequest request) {

        return variantService.update(id, request);
    }

    @DeleteMapping("/variants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        variantService.delete(id);
    }

    @PostMapping("/variants/{id}/sell")
    public VariantResponse sell(
            @PathVariable Long id,
            @Valid @RequestBody SellRequest request) {

        return variantService.sell(id, request);
    }

}