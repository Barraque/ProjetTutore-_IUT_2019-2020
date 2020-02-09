package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@Data
@Table
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    private Role role;//if -1 = admin

    @Column(name = "first_connexion",columnDefinition = "TINYINT(1) default 1",nullable = false)
    //@Type(type = "org.hibernate.type.NumericBooleanType")
    private int firstConnexion;

    @ManyToOne
    @JoinColumn(name = "department_number",nullable = false)
    private Department departmentNumber;// if -1 = admin


    /*public User(String login,String password,String name,String surname,String mail,Role roleid,Department departmentid){
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = "looooooooooooooooo";
        this.mail = mail;
        this.role = roleid;
        this.departmentNumber = departmentid;
    }*/
    public User(){};

    public User(User user){
        this.login = user.login;
        this.password = user.password;
        this.name = user.name;
        this.surname = user.surname;
        this.mail = user.mail;
        this.role = user.role;
        this.departmentNumber = user.departmentNumber;
    }

    public void setFirstConnexion(int firstConnexion) {
        this.firstConnexion = firstConnexion;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
