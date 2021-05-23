package ru.dismals.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dismals.diplom.model.newEntity.Price;

import java.util.Optional;

/**
 * @author Yurii Tyshchuk
 */
public interface PriceRepo extends JpaRepository<Price,Long> {
    Optional<Price> findFirstByOrderById();
}
