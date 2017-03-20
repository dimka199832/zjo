import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimal on 17.03.2017.
 */
public class History implements Serializable{

    // List of last n-count message
    private List<Message> history;
    private int size;

    // Class constructor
    public History(int size){
        this.size = size;
        this.history = new ArrayList<Message>(this.size);
    }

    // Method for control size of history and adding new message
    public void add(Message message){
        if(this.history.size() > this.size){
            this.history.remove(0);
        }
        this.history.add(message);
    }

    // Getter which return history list
    public List<Message> getHistory(){
        return this.history;
    }

}
