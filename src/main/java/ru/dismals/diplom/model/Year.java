package ru.dismals.diplom.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Yurii Tyshchuk
 */
@Entity
@Data
public class Year {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    private int year;

    public Year() {
    }
}
