package ru.dismals.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    private int year;
    private int price;

    @ManyToOne(optional = false)
    private Product product;

}
