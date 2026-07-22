package com.geli.warehouse.service.impl;

import com.geli.warehouse.dto.request.SellRequest;
import com.geli.warehouse.dto.request.VariantRequest;
import com.geli.warehouse.dto.response.VariantResponse;
import com.geli.warehouse.entity.Item;
import com.geli.warehouse.entity.Variant;
import com.geli.warehouse.exception.DuplicateResourceException;
import com.geli.warehouse.exception.OutOfStockException;
import com.geli.warehouse.exception.ResourceNotFoundException;
import com.geli.warehouse.mapper.VariantMapper;
import com.geli.warehouse.repository.ItemRepository;
import com.geli.warehouse.repository.VariantRepository;
import com.geli.warehouse.service.VariantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {

    private final ItemRepository itemRepository;
    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;

    public VariantServiceImpl(
            ItemRepository itemRepository,
            VariantRepository variantRepository,
            VariantMapper variantMapper) {

        this.itemRepository = itemRepository;
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
    }

    @Override
    public VariantResponse create(Long itemId, VariantRequest request) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found"));

        if (variantRepository.existsBySku(request.getSku())) {
            throw new DuplicateResourceException("SKU already exists");
        }

        Variant variant = new Variant();
        variant.setSku(request.getSku());
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        variant.setPrice(request.getPrice());
        variant.setStock(request.getStock());
        variant.setItem(item);

        variant = variantRepository.save(variant);

        return variantMapper.toResponse(variant);
    }

    @Override
    public List<VariantResponse> findByItem(Long itemId) {

        return variantRepository.findByItemId(itemId)
                .stream()
                .map(variantMapper::toResponse)
                .toList();
    }

    @Override
    public VariantResponse findById(Long id) {

        Variant variant = variantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Variant not found"));

        return variantMapper.toResponse(variant);
    }

    @Override
    public VariantResponse update(Long id, VariantRequest request) {

        Variant variant = variantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Variant not found"));

        if (!variant.getSku().equals(request.getSku())
                && variantRepository.existsBySku(request.getSku())) {
            throw new DuplicateResourceException("SKU already exists");
        }

        variant.setSku(request.getSku());
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        variant.setPrice(request.getPrice());
        variant.setStock(request.getStock());

        variant = variantRepository.save(variant);

        return variantMapper.toResponse(variant);
    }

    @Override
    public void delete(Long id) {

        Variant variant = variantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Variant not found"));

        variantRepository.delete(variant);
    }

    @Override
    public VariantResponse sell(Long id, SellRequest request) {

        Variant variant = variantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Variant not found"));

        if (variant.getStock() < request.getQuantity()) {
            throw new OutOfStockException("Stock is insufficient");
        }

        variant.setStock(variant.getStock() - request.getQuantity());

        variant = variantRepository.save(variant);

        return variantMapper.toResponse(variant);
    }
}