package ru.dismals.diplom.model;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Shilov
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
public class Product extends BaseEntity {
    private String nameProduct;

    @OneToMany(orphanRemoval = true, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<YearAndPrice> yearList = new ArrayList<>();

    public Product() {
    }

    public Product(Long id, String nameProduct, List<YearAndPrice> yearList) {
        super(id);
        this.nameProduct = nameProduct;
        this.yearList = yearList;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public List<YearAndPrice> getYearList() {
        return yearList;
    }

    public void setYearList(List<YearAndPrice> yearList) {
        this.yearList = yearList;
    }

    public void addYearAndPrice(YearAndPrice product) {
        yearList.add(product);
        product.setProduct(this);
    }

    public void removeYearAndPrice(YearAndPrice product) {
        yearList.remove(product);
        product.setProduct(null);
    }

}
