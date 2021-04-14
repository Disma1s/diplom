package ru.dismals.diplom.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Vadim Shilov
 */
@Entity
@Data
@Builder
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nameProduct;
    private int price;

    @ManyToOne(optional = false)
    private Year yearProd;

    public Product() {
    }
}
