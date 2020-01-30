package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Data
@Table
@Entity
public class EntrepriseOffer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="entrepriseOfferId",updatable = false,nullable = false)
    private int entrepriseOfferId;

    @Column(name="title",length = 255,nullable = false)
    private String title;

    @Column(name="descripton",columnDefinition = "TEXT",nullable = false)
    private String description;

    @Column(name="birthDate",columnDefinition = "DATE",nullable = false,updatable = false)
    @JsonFormat(pattern = "dd/MM/yyy")
    private Date birthDate;

}
