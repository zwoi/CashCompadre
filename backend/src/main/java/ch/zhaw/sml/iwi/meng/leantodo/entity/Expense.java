package ch.zhaw.sml.iwi.meng.leantodo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String note = "";
    private LocalDate done;

}