package ru.dismals.diplom.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vadim Shilov
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nameProduct;

    @OneToMany(orphanRemoval = true, mappedBy = "product", cascade = CascadeType.ALL)
    private List<YearAndPrice> yearList;

    public void addProduct(YearAndPrice product) {
        yearList.add(product);
        product.setProduct(this);
    }

    public void removeProduct(YearAndPrice product) {
        yearList.remove(product);
        product.setProduct(null);
    }
}
