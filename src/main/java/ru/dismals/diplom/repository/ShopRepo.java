package ru.dismals.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dismals.diplom.model.newEntity.Shop;

/**
 * @author Yurii Tyshchuk
 */
public interface ShopRepo extends JpaRepository<Shop,Long> {
}
