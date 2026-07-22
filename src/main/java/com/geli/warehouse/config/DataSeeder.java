package com.geli.warehouse.config;

import com.geli.warehouse.entity.Item;
import com.geli.warehouse.entity.Variant;
import com.geli.warehouse.repository.ItemRepository;
import com.geli.warehouse.repository.VariantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDatabase(
            ItemRepository itemRepository,
            VariantRepository variantRepository) {

        return args -> {

            if (itemRepository.count() > 0) {
                return;
            }

            Item nike = new Item();
            nike.setName("Nike Air Max");
            nike.setDescription("Running Shoes");

            nike = itemRepository.save(nike);

            Variant v1 = new Variant();
            v1.setItem(nike);
            v1.setSku("NIKE-BLK-40");
            v1.setColor("Black");
            v1.setSize("40");
            v1.setPrice(new BigDecimal("750000"));
            v1.setStock(15);

            Variant v2 = new Variant();
            v2.setItem(nike);
            v2.setSku("NIKE-BLK-41");
            v2.setColor("Black");
            v2.setSize("41");
            v2.setPrice(new BigDecimal("750000"));
            v2.setStock(8);

            Variant v3 = new Variant();
            v3.setItem(nike);
            v3.setSku("NIKE-WHT-40");
            v3.setColor("White");
            v3.setSize("40");
            v3.setPrice(new BigDecimal("800000"));
            v3.setStock(5);

            variantRepository.save(v1);
            variantRepository.save(v2);
            variantRepository.save(v3);

        };

    }

}