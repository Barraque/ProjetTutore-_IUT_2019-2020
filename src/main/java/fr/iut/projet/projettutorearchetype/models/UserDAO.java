package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserDAO {
    private int userId;

    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String name;

    private String surname;

    private String mail;

    private RolesEnum role;//if -1 = admin*/

    private int firstConnection;

    private Department departmentNumber;// if -1 = admin

    public UserDAO(int userId, String login, String password, String name, String surname, String mail, RolesEnum role, Department departmentNumber, int firstConnection) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.role = role;
        this.departmentNumber = departmentNumber;
        this.firstConnection = firstConnection;
    }
}
