package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "days")
public class Day extends BaseClass {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Column(name = "day")
    private int day;
    @Column(name = "pref_calories")
    private double profCalories;
    @Column(name = "def_calories")
    private double defCalories;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "days_products", joinColumns = @JoinColumn(name = "days_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> products = new ArrayList<>();

    public String toString() {
        return "Day(day=" + this.getDay() + ", profCalories=" + this.getProfCalories() + ", defCalories=" + this.getDefCalories() + ", user=" + this.getUser() + ", products=" + this.getProducts() + ")";
    }
}
