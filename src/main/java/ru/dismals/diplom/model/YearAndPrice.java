package ru.dismals.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Yurii Tyshchuk
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YearAndPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int year;
    private int price;

    @ManyToOne(optional = false)
    private Product product;

}
