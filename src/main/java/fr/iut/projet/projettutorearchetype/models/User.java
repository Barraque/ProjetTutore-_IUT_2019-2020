package fr.iut.projet.projettutorearchetype.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userId",updatable = false,nullable = false)
    private int userId;

    @Column(name = "login",nullable = false);
    private String login;

    @Column(name = "password",nullable = false);
    private String password;

    @Column(name = "name",length = 100,nullable = false);
    private String name;

    @Column(name = "surname",length = 100,nullable = false);
    private String surname;

    @Column(name = "mail",nullable = false);
    private String mail;

    @Column(name = "roleId",nullable = false);
    private char roleId;

    @Column(name = "firstConnexion",columnDefinition = "TINYINT(1) default 0",nullable = false);
    private boolean firstConnexion;

    @Column(name = "departmentNumber",nullable = false)
    private int departmentNumber;// if -1 = admin

}
