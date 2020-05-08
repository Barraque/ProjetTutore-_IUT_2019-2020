package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Data
@Table
@Entity
@Getter
public class Offer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="offerId",updatable = false,nullable = false)
    private int offerId;

    @Column(name="title",length = 255,nullable = false)
    private String title;

    @OneToMany
    @Column(name="tags",length = 255,nullable = false)
    private List<Tag> tags;


    @Lob
    @Column(name="offerFile",nullable = false)
    private byte[] offerFile;


    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getOfferFile() {
        return offerFile;
    }

    public void setOfferFile(byte[] offerFile) {
        this.offerFile = offerFile;
    }
}
