package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table
@Entity
public class EntrepriseOffer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="entreprise_offer_id",updatable = false,nullable = false)
    private int entrepriseOfferId;

    @Column(name="title",length = 255,nullable = false)
    private String title;

    @Column(name="descripton",columnDefinition = "TEXT",nullable = false)
    private String description;

    @Column(name="birth_date",columnDefinition = "DATE",nullable = false,updatable = false)
    @JsonFormat(pattern = "dd/MM/yyy")
    private Date birthDate;

}
