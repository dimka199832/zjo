package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dimal on 17.03.2017.
 */
public class UsersList {

    // Create dictionary <username, client>
    private Map<String, Server.ClientThread> onlineClient;

    // Class Constructor
    public UsersList(){
        this.onlineClient = new HashMap<String, Server.ClientThread>();
    }

    // Method which add Client to list
    public void add(String username, Server.ClientThread client){
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
        for(Map.Entry<String, Server.ClientThread> map : onlineClient.entrySet()){
            System.out.println(map.getKey() + " " + map.getValue());
        }
    }

    // Method which return array of users
    public String[] getUsers(){
        return this.onlineClient.keySet().toArray(new String[0]);
    }

    public Map<String, Server.ClientThread> getOnlineClient(){
        return this.onlineClient;
    }

    // Method which return array of clients
    public ArrayList<Server.ClientThread> getClients(){
        ArrayList<Server.ClientThread> clients = new ArrayList<Server.ClientThread>(this.onlineClient.entrySet().size());
        for(Map.Entry<String, Server.ClientThread> map: this.onlineClient.entrySet()){
            clients.add(map.getValue());
        }
        return clients;
    }

    public boolean isInList(String username){
        for(String user: onlineClient.keySet()){
            if(user.equals(username))return true;
        }
        return false;
    }

}
