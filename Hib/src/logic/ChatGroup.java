package logic;

import app.Message;
import java.util.Set;

/**
 * Created by dimal on 17.05.2017.
 */
public class ChatGroup {
    private long Id;
    private Set<Long> Users;
    private Message Msg;

    public ChatGroup(){}

    public void setId(long Id){
        this.Id = Id;
    }

    public void setIdUsers(Set<Long> Users){
        this.Users = Users;
    }

    public void setMsg(Message Msg){
        this.Msg = Msg;
    }

    public long getId(){
        return this.Id;
    }

    public Set<Long> getUsers(){
        return this.Users;
    }

    public Message getMsg(){
        return this.Msg;
    }
}