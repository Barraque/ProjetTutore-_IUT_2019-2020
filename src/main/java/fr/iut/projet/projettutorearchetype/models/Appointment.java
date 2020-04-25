package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;

import javax.persistence.*;

import static org.springframework.http.HttpHeaders.DATE;

@Data
@Table
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="rdvId",updatable = false,nullable = false)
    private int rdvId;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "offer", referencedColumnName = "offerId")
    private Offer offer;

    @Column(name="startTime",columnDefinition = DATE,nullable = false)
    private int startTime;

    @Column(name="duration",columnDefinition = "TINYINT(1) default 1",nullable = false)
    private int duration;



    public int getRdvId() {
        return rdvId;
    }

    public void setRdvId(int rdvId) {
        this.rdvId = rdvId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
