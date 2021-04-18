package ru.dismals.diplom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
