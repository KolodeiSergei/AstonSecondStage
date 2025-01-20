package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseClass {
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

    public String toString() {
        return "Product(name=" + this.getName() + ", price=" + this.getPrice() + ", calories=" + this.getCalories() + ", weight=" + this.getWeight() + ", days=" + this.getDays() + ")";
    }
}
