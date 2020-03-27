package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Table
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="department_id",nullable = false,updatable = false)
    private int departmentId;

    @Column(name="name",length = 100,nullable = false)
    private String name;

}
