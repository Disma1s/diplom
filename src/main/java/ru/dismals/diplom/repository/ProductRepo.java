package ru.dismals.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dismals.diplom.model.Product;

import java.util.Optional;

/**
 * @author Yurii Tyshchuk
 */
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByNameProduct(String NameProduct);
}
