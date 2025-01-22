package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseClass{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "calories")
    private double calories;
    @Column(name = "weight")
    private double weight;
    @ManyToMany(mappedBy = "products")
    private List<Day> days = new ArrayList<>();
}
