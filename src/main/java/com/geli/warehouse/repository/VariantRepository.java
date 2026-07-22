package com.geli.warehouse.repository;

import com.geli.warehouse.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VariantRepository extends JpaRepository<Variant, Long> {

    List<Variant> findByItemId(Long itemId);

    Optional<Variant> findBySku(String sku);

    boolean existsBySku(String sku);

}