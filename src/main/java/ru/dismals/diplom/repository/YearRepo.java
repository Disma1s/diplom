package ru.dismals.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dismals.diplom.model.Year;

import java.util.UUID;

/**
 * @author Yurii Tyshchuk
 */
public interface YearRepo extends JpaRepository<Year, UUID> {
}
