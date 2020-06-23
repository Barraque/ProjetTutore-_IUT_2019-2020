package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Table
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "students, offers"})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagId", updatable = false, nullable = false)
    private int tagId;

    @Column(name="name", nullable = false)
    private String name;

    public String toString(){
        return this.name;
    }

    @ManyToMany(mappedBy = "tags")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<User> students;

    @ManyToMany(mappedBy = "tags")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Offer> offers;
}
