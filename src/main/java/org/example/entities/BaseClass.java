package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
@Table(name = "baseclass")
public abstract class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "entity_seq", sequenceName = "entity_sequence", allocationSize = 1)
    private int id;

    public String toString() {
        return "BaseClass(id=" + this.getId() + ")";
    }
}
