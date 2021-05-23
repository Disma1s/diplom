package ru.dismals.diplom.model.newEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.dismals.diplom.model.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
public class Price extends BaseEntity {
    int price;
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    NewProduct newProduct;
}
