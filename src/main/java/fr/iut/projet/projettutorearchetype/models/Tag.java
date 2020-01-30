package fr.iut.projet.projettutorearchetype.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tagId", updatable = false, nullable = false)
    private int tagId;

    @Column(name="departmentId",nullable = false)
    private int departmentId;

    @Column(name="name", nullable = false)
    private String name;
}
