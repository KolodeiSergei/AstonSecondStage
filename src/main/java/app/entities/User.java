package app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "weight")
    private double weight;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Day> days = new ArrayList<Day>();

    public String toString() {
        return "User(id=" + this.getId() + ", name=" + this.getName() + ", age=" + this.getAge() + ", weight=" + this.getWeight() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", days=" + this.getDays().size() + ")";
    }
}
