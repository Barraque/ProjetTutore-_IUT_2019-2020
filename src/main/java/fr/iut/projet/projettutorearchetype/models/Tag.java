package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", updatable = false, nullable = false)
    private int tagId;

    /*
    @Column(name="department_id",nullable = false)
    private int departmentId;
    */

    @Column(name="name", nullable = false)
    private String name;

    public String toString(){
        return this.name;
    }
}
