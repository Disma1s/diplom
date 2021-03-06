package ru.dismals.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dismals.diplom.model.old.YearAndPrice;

/**
 * @author Yurii Tyshchuk
 */
public interface YearRepo extends JpaRepository<YearAndPrice, Long> {
}
