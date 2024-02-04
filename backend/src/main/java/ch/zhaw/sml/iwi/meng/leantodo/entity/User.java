package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter 
@NoArgsConstructor
@Table(name="USERTABLE")
public class User {

    @Id
    private String loginName;

    @JsonIgnore
    private String passwordHash;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();

}