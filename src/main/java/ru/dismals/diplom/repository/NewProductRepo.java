package ru.dismals.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dismals.diplom.model.newEntity.NewProduct;

/**
 * @author Yurii Tyshchuk
 */
public interface NewProductRepo extends JpaRepository<NewProduct,Long> {
}
