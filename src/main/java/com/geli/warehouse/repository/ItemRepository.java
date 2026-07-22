package com.geli.warehouse.repository;

import com.geli.warehouse.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}