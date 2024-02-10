package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Category() {
    }

    public Category(String name, Integer limitAmount) {
        this.name = name;
        this.limitAmount = limitAmount;
    }

    private String name;
    private Integer limitAmount;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Expense> expenses = new ArrayList<>();
}