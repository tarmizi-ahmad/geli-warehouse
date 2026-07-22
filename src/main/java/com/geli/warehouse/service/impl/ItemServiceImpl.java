package com.geli.warehouse.service.impl;

import com.geli.warehouse.dto.request.ItemRequest;
import com.geli.warehouse.dto.response.ItemResponse;
import com.geli.warehouse.entity.Item;
import com.geli.warehouse.exception.ResourceNotFoundException;
import com.geli.warehouse.mapper.ItemMapper;
import com.geli.warehouse.repository.ItemRepository;
import com.geli.warehouse.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository,
                           ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemResponse create(ItemRequest request) {

        Item item = new Item();
        item.setName(request.getName());
        item.setDescription(request.getDescription());

        item = itemRepository.save(item);

        return itemMapper.toResponse(item);
    }

    @Override
    public List<ItemResponse> findAll() {

        return itemRepository.findAll()
                .stream()
                .map(itemMapper::toResponse)
                .toList();
    }

    @Override
    public ItemResponse findById(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found"));

        return itemMapper.toResponse(item);
    }

    @Override
    public ItemResponse update(Long id, ItemRequest request) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found"));

        item.setName(request.getName());
        item.setDescription(request.getDescription());

        item = itemRepository.save(item);

        return itemMapper.toResponse(item);
    }

    @Override
    public void delete(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found"));

        itemRepository.delete(item);
    }
}