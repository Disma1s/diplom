package ru.dismals.diplom.model.old;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.dismals.diplom.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Yurii Tyshchuk
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YearAndPrice extends BaseEntity {
    private int year;
    private int price;
    @JsonIgnore
    @ManyToOne(optional = false)
    private Product product;
}
