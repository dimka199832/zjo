package app;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dimal on 17.03.2017.
 */
public class Message implements Serializable {

    //Message types
    public static final int INFO = 0;
    public static final int CONFIG = 1;
    public static final int MESSAGE = 2;
    public static final int DISCONNECT = 3;
    // Create private field for username
    private String username;
    // Create private field for data
    private String data;
    // Create private field for array of users, to who need send data
    private String[] users = null;
    // Create private field for save time when was created data
    private Date time;
    // Create private field for type data
    private int type;

    // Constructor for data to one person
    public Message(String username, String data){
        this.username = username;
        this.data = data;
        this.time = Calendar.getInstance().getTime();
    }

    public Message(String username, String data, int type){
        this(username, data);
        this.type = type;
    }

    // Constructor for data to many people
    public Message(String username, String data, int type, String[] users){
        this(username, data, type);
        this.users = users;
    }

    public boolean toUser(String username){
        if(users == null) return false;
        for(String user : users){
            if(user.equals(username) || this.username == username) return true;
        }
        return false;
    }

    // Getters
    public String getUsername(){
        return this.username;
    }
    public Object getData(){
        return this.data;
    }
    public String[] getUsers(){
        return this.users;
    }
    public Date getDate(){
        return this.time;
    }
    public int getType(){
        return this.type;
    }
}
