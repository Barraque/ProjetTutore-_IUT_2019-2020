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
    @JoinColumn(name = "user", referencedColumnName = "userId", nullable = false)
    private User user;


    @Column(name="status",length = 255,nullable = false)
    private int status;

    @Lob
    @Column(name="cvFile",columnDefinition = "BLOB",nullable = false)
    private List<Byte> cvFile;


    public int getCvId() {
        return cvId;
    }

    public void setCvId(int cvId) {
        this.cvId = cvId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Byte> getCvFile() {
        return cvFile;
    }

    public void setCvFile(List<Byte> cvFile) {
        this.cvFile = cvFile;
    }
}
