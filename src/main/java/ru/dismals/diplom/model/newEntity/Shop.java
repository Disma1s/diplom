package ru.dismals.diplom.model.newEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.dismals.diplom.model.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurii Tyshchuk
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shop extends BaseEntity {
    String name;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "shop")
    List<NewProduct> productList = new ArrayList<>();
}
