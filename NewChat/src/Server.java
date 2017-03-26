import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Created by dimal on 18.03.2017.
 */
public class Server {

    private UsersList usersList;
    private History history;

    public Server(){
        this.usersList = new UsersList();
        this.history = new History(50);
    }

    private void broadcast(Message message){
        for(Map.Entry<String, ClientThread> map: usersList.getOnlineClient().entrySet()){
            if(!message.getUsername().equals(map.getKey())){
                map.getValue().sendMessage(message);
            }
        }
    }

    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(1500);
            while (true) {
                Socket socket = null;
                while (socket == null) {
                    socket = serverSocket.accept();
                }
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                ClientThread ct = new ClientThread(socket, oos, ois);
                ct.start();
            }
        } catch (IOException e){
            System.out.println("Can't create server.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    class ClientThread extends Thread{

        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;

        public ClientThread(Socket socket, ObjectOutputStream oos, ObjectInputStream ois){
            this.socket = socket;
            this.ois = ois;
            this.oos = oos;
        }

        public void run(){

            try{
                String username = null;
                boolean flag;
                do {
                    try {
                        username = (String) ois.readObject();
                        System.out.println(username);
                    } catch (ClassNotFoundException e){}
                    flag = usersList.isInList(username);
                    oos.writeObject(flag);
                } while (flag);

                usersList.add(username, this);

                for (Message message : history.getHistory()) {
                    oos.writeObject(message);
                }

                while (true) {
                    if(socket.isConnected()) {
                        Message message = (Message) ois.readObject();
                        if (message.getMessage().equals("exit")) {
                            break;
                        }
                        history.add(message);
                        broadcast(message);
                    }else break;
                }
                usersList.remove(username);
                close();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        public void close(){
            try {
                if(socket != null) socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            try {
                if(ois != null) ois.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            try {
                if(oos != null) oos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        public void sendMessage(Message message){
            try {
                oos.writeObject(message);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
