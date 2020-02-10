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
    @Column(name="user_id",updatable = false,nullable = false)
    private int userId;

    @Column(name = "login",nullable = false,unique = true)
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "name",length = 100,nullable = true)
    private String name;

    @Column(name = "surname",length = 100,nullable = false)
    private String surname;

    @Column(name = "mail",nullable = false)
    private String mail;

    @Column(name = "role", nullable = false)
    private RolesEnum role;//if -1 = admin*/

    @Column(name = "first_connexion",columnDefinition = "TINYINT(1) default 1",nullable = false)
    private int firstConnexion;

    @ManyToOne
    @JoinColumn(name = "department_number",nullable = false)
    private Department department_number;// if -1 = admin

    public User(){};

    public User(User user){
        this.login = user.login;
        this.password = user.password;
        this.name = user.name;
        this.surname = user.surname;
        this.mail = user.mail;
        this.role = user.role;
        this.department_number= user.department_number;
    }

    public void setFirstConnexion(int firstConnexion) {
        this.firstConnexion = firstConnexion;
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

    public int getFirstConnexion() {
        return firstConnexion;
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
        return department_number;
    }
}
