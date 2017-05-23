package app;

/**
 * Created by dimal on 09.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow(new Client());
        ChatWindow window;
        Client client;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!loginWindow.isWork()) {
                client = loginWindow.getClient();
                break;
            }
        }
        window = new ChatWindow(client);
    }
}
