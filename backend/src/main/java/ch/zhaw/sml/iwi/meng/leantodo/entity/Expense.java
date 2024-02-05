package ch.zhaw.sml.iwi.meng.leantodo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String note = new String();

    @Temporal(TemporalType.DATE)
    private LocalDate done;

}