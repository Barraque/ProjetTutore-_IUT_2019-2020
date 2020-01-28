package fr.iut.projet.projettutorearchetype.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Etudiant {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @Column(name = "numero_etudiant", nullable = false)
    private Integer numero_etudiant;

    @Column (name = "nom", nullable = false)
    private String nom;


    public Etudiant (final Integer numero_etudiant, final String nom){
        this.numero_etudiant = numero_etudiant;
        this.nom = nom;
    }

}
