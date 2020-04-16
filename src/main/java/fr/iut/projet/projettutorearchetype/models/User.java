package fr.iut.projet.projettutorearchetype.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Table
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id",updatable = false,nullable = false)
    @Min(value = 0, message = "Id must be over 0")
    private int userId;

    @Column(name = "login",nullable = false,unique = true)
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "firstname",length = 100,nullable = true)
    private String name;

    @Column(name = "lastname",length = 100,nullable = false)
    private String surname;

    @Column(name = "mail",nullable = false)
    private String mail;

    @Column(name = "role", nullable = false)
    private RolesEnum role;//if -1 = admin*/

    @Column(name = "first_connexion",columnDefinition = "TINYINT(1) default 1",nullable = false)
    private int firtConnection;

    @ManyToOne
    @JoinColumn(name = "department_number",nullable = false)
    private Department departmentNumber;// if -1 = admin


    public User(){};

    public User(User user){
        this.login = user.login;
        this.password = user.password;
        this.name = user.name;
        this.surname = user.surname;
        this.mail = user.mail;
        this.role = user.role;
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
        this.firtConnection = userdao.getFirstConnection();
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
                this.getFirtConnection()
        );
    }
  
    public void setFirtConnection(int firtConnection) {
        this.firtConnection = firtConnection;
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

    public int getFirtConnection() {
        return firtConnection;
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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
    }
}
