import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by dimal on 17.03.2017.
 */
public class Client {

    // Create field for port and server;
    private String server;
    private int port;
    // Create Socket field
    private Socket socket;
    // Create input stream
    private ObjectInputStream ois;
    // Create output stream
    private ObjectOutputStream oos;
    // Create field username
    private String username;

    // Constructor for class
    public Client(String server, int port, String username){
        this.server = server;
        this.port = port;
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

    // Method which try create Client
    public boolean start(){
        try {
            this.socket = new Socket(InetAddress.getLocalHost(), 1500);
        } catch (UnknownHostException e){
            e.printStackTrace();
            return false;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

        new ServerListener().start();

        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            System.out.println("Cannot get stream.");
            e.printStackTrace();
            return false;
        }

        try {
            this.oos.writeObject(username + " was connected.");
        } catch (IOException e){
            System.out.println("Cannot send message.");
            return false;
        }
        return true;
    }

    public void sendMessage(Message message){
        try {
            this.oos.writeObject(message);
        } catch (IOException e){
            System.out.println("Failed sending message.");
            e.printStackTrace();
        }
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
        Client client = new Client("localhost", 1500, args[0]);

        if(!client.start()) return;

        Scanner scan = new Scanner(System.in);
        String message;

        while (true){
            message = scan.next();
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
            try {
                Message message = (Message) ois.readObject();

                System.out.println(message.getUsername() + ": " + message.getMessage() + " (" + message.getDate() + ')');
                System.out.print("> ");
            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
