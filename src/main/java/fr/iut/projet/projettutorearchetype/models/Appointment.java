package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;

import javax.persistence.*;

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

    @Column(name="startTime", nullable = false)
    private int startTime;

    @Column(name="duration")
    private int duration;
}
