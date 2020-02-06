package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table
@Entity
@Getter
public class Offer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="offer_id",updatable = false,nullable = false)
    private int OfferId;

    @Column(name="title",length = 255,nullable = false)
    private String title;

    @Column(name="descripton",columnDefinition = "TEXT",nullable = false)
    private String description;

    @Column(name="creation_date",columnDefinition = "DATE",nullable = true,updatable = false)
    @JsonFormat(pattern = "dd/MM/yyy")
    private Date creationDate;

}
