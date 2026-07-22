package com.geli.warehouse.service;

import com.geli.warehouse.dto.request.SellRequest;
import com.geli.warehouse.dto.request.VariantRequest;
import com.geli.warehouse.dto.response.VariantResponse;

import java.util.List;

public interface VariantService {

    VariantResponse create(Long itemId, VariantRequest request);

    List<VariantResponse> findByItem(Long itemId);

    VariantResponse findById(Long id);

    VariantResponse update(Long id, VariantRequest request);

    void delete(Long id);

    VariantResponse sell(Long id, SellRequest request);

}