package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="departmentId",nullable = false,updatable = false)
    private int departmentId;

    @Column(name="name",length = 100,nullable = false)
    private String name;
}
