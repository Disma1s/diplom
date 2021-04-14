package ru.dismals.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author Yurii Tyshchuk
 */
@Entity
@Data
@Builder
@AllArgsConstructor
public class Year {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    private int year;

    @OneToMany(orphanRemoval = true, mappedBy = "yearProd", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Year() {
    }

    public void addProduct(Product product) {
        productList.add(product);
        product.setYearProd(this);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
        product.setYearProd(null);
    }
}
