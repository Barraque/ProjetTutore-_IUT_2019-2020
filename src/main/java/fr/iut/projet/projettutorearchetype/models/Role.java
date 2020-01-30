package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roleId",updatable = false,nullable = false)
    private int roleId;

    @Column(name="name",length = 100,nullable = false)
    private String name;
}
