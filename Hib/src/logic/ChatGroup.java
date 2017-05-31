package logic;

import app.Message;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dimal on 17.05.2017.
 */
@Entity
@Table(name = "chatgroup", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Message")})
public class ChatGroup {

    private Long Id_Group;
    private String Msg;
    private Set<User> Users = new HashSet<User>(0);

    public ChatGroup(){}

    public ChatGroup(String Msg){
        this.Msg = Msg;
    }

    public ChatGroup(String Msg, Set<User> Users){
        this.Msg = Msg;
        this.Users = Users;
    }

    public void setId_Group(Long Id_Group){
        this.Id_Group = Id_Group;
    }

    public void setMsg(String Msg){
        this.Msg = Msg;
    }

    public void setUsers(Set<User> Users){
        this.Users = Users;
    }

    @Id
    @Column(name = "Id_Group")
    @GeneratedValue
    public Long getId_Group(){
        return this.Id_Group;
    }

    @Column(name = "Message")
    public String getMsg(){
        return this.Msg;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "idgroup", joinColumns = {
            @JoinColumn(name = "Id_Group") },
            inverseJoinColumns = { @JoinColumn(name = "Id_User", nullable = false) })
    public Set<User> getUsers(){
        return this.Users;
    }
}