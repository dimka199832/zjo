package logic;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by dimal on 17.05.2017.
 */
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Login"),
        @UniqueConstraint(columnNames = "Password") })
public class User {

    private Long Id_User;
    private String Login;
    private String Password;

    private UserInfo Info;
    private Set<ChatGroup> Groups = new HashSet<ChatGroup>(0);

    /*Constructors*/

    public User(){}

    /*Setters*/

    public void setId_User(Long Id_User){
        this.Id_User = Id_User;
    }

    public void setLogin(String Login){
        this.Login = Login;
    }

    public void setPassword(String Password){
        this.Password = Password;
    }

    public void setInfo(UserInfo Info){
        this.Info = Info;
    }

    public void setGroups(Set<ChatGroup> Groups){
        this.Groups = Groups;
    }

    /*Getters*/

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "Id_User", unique = true, nullable = false)
    public Long getId_User(){
        return this.Id_User;
    }

    @Column(name = "Login")
    public String getLogin(){
        return this.Login;
    }

    @Column(name = "Password")
    public String getPassword(){
        return this.Password;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_UserInfo")
    public UserInfo getInfo(){
        return this.Info;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "idgroup", joinColumns = {
            @JoinColumn(name = "Id_User", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "Id_Group") })
    public Set<ChatGroup> getGroups(){
        return this.Groups;
    }
}
