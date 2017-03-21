import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dimal on 18.03.2017.
 */
public class Server {

    private UsersList usersList;

    public Server(){
        this.usersList = new UsersList();
    }

    private void broadcast(Message message){
        for(ClientThread ct: usersList.getClients()){
            ct.sendMessage(message);
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
                ClientThread ct = new ClientThread(socket, oos, ois);
                usersList.add(username, ct);
                ct.start();
            }
        } catch (IOException e){
            System.out.println("Cannot create server.");
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
                while (true) {
                    Message message = (Message) ois.readObject();
                    broadcast(message);
                }
            } catch (ClassNotFoundException e){
                e.printStackTrace();
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
