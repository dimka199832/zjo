import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dimal on 17.03.2017.
 */
public class Message implements Serializable {

    // Create private field for username
    private String username;
    // Create private field for message
    private String message;
    // Create private field for array of users, to who need send message
    private String[] users;
    // Create private field for save time when was created message
    private Date time;

    // Constructor for message to one person
    public Message(String username, String message){
        this.username = username;
        this.message = message;
        this.time = Calendar.getInstance().getTime();
    }

    // Constructor for message to many people
    public Message(String username, String message, String[] users){
        this(username, message);
        this.users = users;
    }

    // Getters
    public String getUsername(){
        return this.username;
    }
    public String getMessage(){
        return this.message;
    }
    public String[] getUsers(){
        return this.users;
    }
    public String getDate(){
        return new Date(this.time.getTime()).toString();
    }
}
