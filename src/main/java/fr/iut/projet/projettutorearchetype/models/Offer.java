package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Table
@Entity
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "tags"})
public class Offer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="offerId",updatable = false,nullable = false)
    private int offerId;

    @Column(name="title",length = 255,nullable = false)
    private String title;

    @Lob
    @Column(name="offerFile",columnDefinition = "LONGBLOB",nullable = false, length = 10000000)
    private byte[] offerFile;

    @ManyToMany
    @JoinTable(
        name = "accepted_offers",
        joinColumns = @JoinColumn(name = "offerId"),
        inverseJoinColumns = @JoinColumn(name = "departmentId"))
    Set<Department> acceptedDepartment;

    @ManyToMany
    @JoinTable(
            name = "tags_offers",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Tag> tags;
}
