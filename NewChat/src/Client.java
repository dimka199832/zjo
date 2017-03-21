import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by dimal on 17.03.2017.
 */
public class Client{

    // Create field for port and server;
    private static String server = "localhost";
    private static int port = 1500;
    // Create Socket field
    private Socket socket;
    // Create input stream
    private ObjectInputStream ois;
    // Create output stream
    private ObjectOutputStream oos;
    // Create field username
    private String username;

    // Constructor for class
    public Client(Socket socket, ObjectOutputStream oos, ObjectInputStream ois, String username){
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
        this.username = username;
    }

    // Getters
    public ObjectInputStream getOIS(){
        return this.ois;
    }
    public ObjectOutputStream getOOS(){
        return this.oos;
    }
    public String getUsername() {
        return this.username;
    }

    public void sendMessage(Message message){
        try {
            this.oos.writeObject(message);
        } catch (IOException e){
            System.out.println("Failed sending message.");
            e.printStackTrace();
        }
    }

    public void start(){
        new ServerListener().start();
    }

    // Method which try close all connection
    public void disconnect(){
        try {
            this.oos.close();
        } catch (IOException e){
            System.out.println("Cannot close output stream.");
            return;
        }
        try {
            this.ois.close();
        } catch (IOException e){
            System.out.println("Cannot close input stream.");
        }
        try {
            this.socket.close();
        } catch (IOException e){
            System.out.println("Cannot close socket.");
        }
    }

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getByName(server), port);
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            System.out.println("Cannot get stream.");
            e.printStackTrace();
        }

        boolean incorrect = false;
        String username = null;
        do{
            try {
                System.out.print("Please enter username: ");
                username = (new Scanner(System.in)).next();
                oos.writeObject(username);
                try {
                    incorrect = (Boolean) ois.readObject();
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }while (incorrect && username != null);

        Client client = new Client(socket, oos, ois, username);

        client.start();

        Scanner scan = new Scanner(System.in);
        String message;

        while (true){
            System.out.print("> ");
            message = scan.nextLine();
            if(message.equals("exit")){
                break;
            } else {
                client.sendMessage(new Message(client.username, message));
            }
        }

        client.disconnect();
    }

    public class ServerListener extends Thread{
        public void run(){

            SimpleDateFormat sdf = new SimpleDateFormat("H:m:s");
            String time;

            while (true) {
                try {
                    Message message = (Message) ois.readObject();

                    time = sdf.format(Calendar.getInstance().getTime());

                    System.out.println(message.getUsername() + ": " + message.getMessage() + " (" + time + ')');
                    System.out.print("> ");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
