package ch.zhaw.sml.iwi.meng.leantodo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    private String roleName;

}