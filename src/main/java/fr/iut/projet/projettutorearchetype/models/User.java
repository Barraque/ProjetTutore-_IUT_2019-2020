package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.util.EnumResolver;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Table
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="userId",updatable = false,nullable = false)
    private int userId;

    @Column(name = "login",nullable = false,unique = true)
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "firstname",length = 100,nullable = true)
    private String firstname;

    @Column(name = "lastname",length = 100,nullable = false)
    private String lastname;

    @Column(name = "mail",nullable = false)
    private String mail;

    @Column(name = "role", nullable = false)
    private RolesEnum role;//if -1 = admin*/

    @Column(name = "firstConnexion",columnDefinition = "TINYINT(1) default 1",nullable = false)
    private int firstConnexion;

    @ManyToOne
    @JoinColumn(name = "departmentNumber",nullable = false)
    private Department departmentNumber;// if -1 = admin

    public User(){};

    public User(User user){
        this.login = user.login;
        this.password = user.password;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.mail = user.mail;
        this.role = user.role;
        this.departmentNumber= user.departmentNumber;
        this.departmentNumber= user.departmentNumber;
    }

    public User(UserDAO userdao){
        this.login = userdao.getLogin();
        this.password = userdao.getPassword();
        this.name = userdao.getName();
        this.surname = userdao.getSurname();
        this.mail = userdao.getMail();
        this.role = userdao.getRole();
        this.departmentNumber = userdao.getDepartmentNumber();
        this.firstConnection = userdao.getFirstConnection();
    }

    public UserDAO convertToUserDAO(){
        return new UserDAO(
                this.getUserId(),
                this.getLogin(),
                null,
                this.getName(),
                this.getSurname(),
                this.getMail(),
                this.getRole(),
                this.getDepartmentNumber(),
                this.getFirstConnection()
        );
    }

    public void setFirstConnection(int firstConnection) {
        this.firstConnection = firstConnection;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(getRoles().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public int getFirstConnection() {
        return firstConnection;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public Collection<RolesEnum> getRoles() {
        ArrayList<RolesEnum> role = new ArrayList<RolesEnum>();
        role.add(this.role);
        return role;
    }

    public Department getDepartmentSet() {
        return departmentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
        return departmentNumber;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Department getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Department departmentNumber) {
        this.departmentNumber = departmentNumber;
    }
}
