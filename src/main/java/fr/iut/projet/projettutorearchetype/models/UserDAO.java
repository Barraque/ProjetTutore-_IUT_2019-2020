package fr.iut.projet.projettutorearchetype.models;

import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class UserDAO {
    private int userId;

    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String firstname;

    private String lastname;

    private String mail;

    private RolesEnum role;//if -1 = admin*/

    private int firstConnection;

    private Department departmentNumber;// if -1 = admin

    public UserDAO(int userId, String login, String password, String firstname, String lastname, String mail, RolesEnum role, Department departmentNumber, int firstConnection) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.role = role;
        this.departmentNumber = departmentNumber;
        this.firstConnection = firstConnection;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }

    public int getFirstConnection() {
        return firstConnection;
    }

    public void setFirstConnection(int firstConnection) {
        this.firstConnection = firstConnection;
    }

    public Department getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Department departmentNumber) {
        this.departmentNumber = departmentNumber;
    }
}
