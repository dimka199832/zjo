import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dimal on 17.03.2017.
 */
public class UsersList {

    // Create dictionary <username, client>
    private Map<String, Client> onlineClient;

    // Class Constructor
    public UsersList(){
        this.onlineClient = new HashMap<String, Client>();
    }

    // Method which add Client to list
    public void add(String username, Client client){
        if (this.onlineClient.containsKey(username)){
            System.out.println("This username is unavailable.");
            return;
        } else {
            this.onlineClient.put(username, client);
        }
    }

    // Method which remove Client from list
    public void remove(String username){
        this.onlineClient.remove(username);
    }

    // Method which return array of users
    public String[] getUsers(){
        return this.onlineClient.keySet().toArray(new String[0]);
    }

    // Method which return array of clients
    public ArrayList<Client> getClients(){
        ArrayList<Client> clients = new ArrayList<Client>(this.onlineClient.entrySet().size());
        for(Map.Entry<String, Client> map: this.onlineClient.entrySet()){
            clients.add(map.getValue());
        }
        return clients;
    }

}
