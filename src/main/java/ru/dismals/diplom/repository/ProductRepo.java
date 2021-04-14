package ru.dismals.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dismals.diplom.model.Product;

/**
 * @author Yurii Tyshchuk
 */
public interface ProductRepo extends JpaRepository<Product,Long> {
}
