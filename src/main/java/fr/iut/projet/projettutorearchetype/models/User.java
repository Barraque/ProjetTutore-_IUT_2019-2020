package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id",updatable = false,nullable = false)
    private int userId;

    @Column(name = "login",nullable = false)
    private String login;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "surname",length = 100,nullable = false)
    private String surname;

    @Column(name = "mail",nullable = false)
    private String mail;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @Column(name = "first_connexion",columnDefinition = "TINYINT(1) default 0",nullable = false)
    private boolean firstConnexion;

    @Column(name = "department_number",nullable = false)
    private int departmentNumber;// if -1 = admin

}
