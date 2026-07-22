package com.geli.warehouse.service;

import com.geli.warehouse.dto.request.ItemRequest;
import com.geli.warehouse.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {

    ItemResponse create(ItemRequest request);

    List<ItemResponse> findAll();

    ItemResponse findById(Long id);

    ItemResponse update(Long id, ItemRequest request);

    void delete(Long id);

}