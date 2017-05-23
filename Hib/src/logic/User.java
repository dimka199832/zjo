package logic;

import java.util.Set;

/**
 * Created by dimal on 17.05.2017.
 */
public class User {
    private long Id;
    private Set<Long> Groups;
    private String Login;
    private String Password;
    private UserInfo Info;

    public User(){}

    public void setId(long Id){
        this.Id = Id;
    }

    public void setGroups(Set<Long> Groups){
        this.Groups = Groups;
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

    public long getId(){
        return this.Id;
    }

    public Set<Long> getGroups(){
        return this.Groups;
    }

    public String getLogin(){
        return this.Login;
    }

    public String getPassword(){
        return this.Password;
    }

    public UserInfo getInfo(){
        return this.Info;
    }
}
