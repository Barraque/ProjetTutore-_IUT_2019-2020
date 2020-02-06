package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Table
@Entity
@Getter

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id",updatable = false,nullable = false)
    private int roleId;

    @Column(name="name",length = 100,nullable = false)
    private String name;
}
