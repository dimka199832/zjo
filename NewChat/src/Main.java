<<<<<<< HEAD
/**
 * Created by dimal on 09.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        //System.out.println("Here");
        //loginWindow.run();
        //System.out.println("Work");
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
=======
/**
 * Created by dimal on 09.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        //System.out.println("Here");
        //loginWindow.run();
        //System.out.println("Work");
        LoginWindow loginWindow = new LoginWindow(new Client());
        ChatWindow window;
        Client client;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!loginWindow.isVisible()) {
                client = loginWindow.getClient();
                break;
            }
        }
        window = new ChatWindow(client);
    }
}
>>>>>>> 0fa5fb1183fde38ee431f6524415ee608e3572bc
