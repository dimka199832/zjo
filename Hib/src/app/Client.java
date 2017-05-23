package app;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;

/**
 * Created by dimal on 17.03.2017.
 */
public class Client{

    // Create field for port and server;
    private static String server = "localhost"; //139.59.130.172
    private static int port = 1500;
    // Create Socket field
    private Socket socket;
    // Create input stream
    private ObjectInputStream ois;
    // Create output stream
    private ObjectOutputStream oos;
    // Create field username
    private String username;
    //private static ChatWindow window;
    private ChatWindow window;
    // Server Listener
    private ServerListener serverListener;

    // Constructor for class
    public Client(){
        this.window = null;
        try {
            this.socket = new Socket(InetAddress.getByName(server), port);
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            System.out.println("Error creating client");
        }
    }

    public boolean setUsername(String username){
        boolean incorrect = false;
        if(username != null) this.username = username;
        try {
            oos.writeObject(this.username);
            try {
                incorrect = (Boolean) ois.readObject();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return incorrect;
    }

    public void setWindow(ChatWindow window){
        this.window = window;
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
    public Message getMessage(){
        try {
            Message message = (Message) ois.readObject();
            return message;
        } catch (IOException e){
            System.out.println("Error can't get message");
        } catch (ClassNotFoundException e){
            System.out.println("Unexpected class");
        }
        return null;
    }

    public void start(){

        sendMessage(new Message(username, username + " connected.", Message.INFO));

        Message message = null;
        try {
            message = (Message) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(message != null){
            window.addUserToList(message.getUsers());
        }


        serverListener = new ServerListener();
        serverListener.start();
    }

    // Method which try close all connection
    public void disconnect(){

        sendMessage(new Message(username, username + " disconnected.", Message.DISCONNECT));

        try {
            this.socket.close();
        } catch (IOException e){
            System.out.println("Can't close socket.");
        }
        try {
            this.oos.close();
        } catch (IOException e){
            System.out.println("Can't close output stream.");
            return;
        }
        try {
            this.ois.close();
        } catch (IOException e) {
            System.out.println("Can't close input stream.");
        }
    }

    public class ServerListener extends Thread{
        public void run(){

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time;

            while (true) {
                Message message = getMessage();
                if(message != null) {
                    time = sdf.format(message.getDate());

                    if (message.getType() == Message.MESSAGE) {
                        window.addText(message.getUsername() + ": " +
                                message.getData() + " (" + time + ")");
                    }
                    if (message.getType() == Message.INFO) {
                        window.addText(message.getData() + " (" + time + ")");
                    }
                    if (message.getType() == Message.CONFIG){
                        window.addUserToList(message.getUsers());
                    }
                    if (message.getType() == Message.DISCONNECT){
                        window.removeUserFromList(message.getUsername());
                    }

                }else {
                    if(socket.isClosed()){
                        break;
                    }
                }
            }
        }
    }
}
