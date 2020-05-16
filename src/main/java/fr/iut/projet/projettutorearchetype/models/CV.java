package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Data
@Table
@Entity
public class CV {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cvId",updatable = false,nullable = false)
    private int cvId;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private User user;


    @Column(name="status",length = 255,nullable = false)
    private int status;

    @Lob
    @Column(name="cvFile",columnDefinition = "LONGBLOB",nullable = false, length = 10000000)
    private byte[] cvFile;
}
