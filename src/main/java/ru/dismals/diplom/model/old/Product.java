package ru.dismals.diplom.model.old;


import lombok.*;
import ru.dismals.diplom.model.BaseEntity;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity {
    private String nameProduct;

    @OneToMany(orphanRemoval = true, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<YearAndPrice> yearList = new ArrayList<>();

    public void addYearAndPrice(YearAndPrice product) {
        yearList.add(product);
        product.setProduct(this);
    }

    public void removeYearAndPrice(YearAndPrice product) {
        yearList.remove(product);
        product.setProduct(null);
    }

}
