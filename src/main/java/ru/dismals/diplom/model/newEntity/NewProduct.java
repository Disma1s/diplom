package ru.dismals.diplom.model.newEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.dismals.diplom.model.BaseEntity;

import javax.persistence.*;
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
public class NewProduct extends BaseEntity {
    String name;
    @JsonIgnore
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    Shop shop;
    @OneToMany(mappedBy = "newProduct",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    List<Price> prices = new ArrayList<>();
}
