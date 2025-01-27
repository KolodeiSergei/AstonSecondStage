package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "days")
public class Day{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "day")
    private int day;
    @Column(name = "pref_calories")
    private double profCalories;
    @Column(name = "def_calories")
    private double defCalories;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn
    @ToString.Exclude
    private User user;
    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinTable(name = "days_products",joinColumns = @JoinColumn(name = "days_id"),
    inverseJoinColumns = @JoinColumn(name = "products_id"))
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();
}
