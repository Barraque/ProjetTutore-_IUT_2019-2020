package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
public class UserDAO {
    private int userId;

    private String login;

    private String password;

    private String name;

    private String surname;

    private String mail;

    private RolesEnum role;//if -1 = admin*/

    private int firstConnexion;

    private Department department_number;// if -1 = admin

    public UserDAO(int userId, String login, String password, String name, String surname, String mail, RolesEnum role, int firstConnexion, Department department_number) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.role = role;
        this.firstConnexion = firstConnexion;
        this.department_number = department_number;
    }
}
