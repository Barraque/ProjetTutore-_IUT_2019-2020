package fr.iut.projet.projettutorearchetype.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.*;

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

    @OneToMany
    @JoinTable
    //@JoinTable(name = "role_id",joinColumns = @JoinColumn(name=) nullable = false)
    private Set<Role> roles;//if -1 = admin

    @Column(name = "first_connexion",columnDefinition = "TINYINT(1) default 1",nullable = false)
    //@Type(type = "org.hibernate.type.NumericBooleanType")
    private int firstConnexion;

    @ManyToOne
    @JoinColumn(name = "department_number",nullable = false)
    private Department department_number;// if -1 = admin


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
        this.roles = user.roles;
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
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
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

    public Set<Role> getRoles() {
        return roles;
    }

    public Department getDepartmentSet() {
        return department_number;
    }
}
