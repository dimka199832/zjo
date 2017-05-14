<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by dimal on 08.04.2017.
 */
public class LoginWindow extends Frame{

    private JTextField loginText;
    private JPasswordField password;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JButton buttonConnect;
    private Client client;
    private boolean work = true;

    public LoginWindow(Client client) {
        super("Login");

        this.client = client;

        Font font = new Font(null, Font.CENTER_BASELINE, 22);

        loginText = new JTextField(25);
        loginText.setBounds(300, 230, 400, 40);
        loginText.setHorizontalAlignment(JTextField.CENTER);
        loginText.setFont(font);


        password = new JPasswordField(25);
        password.setBounds(300, 300, 400, 40);
        password.setHorizontalAlignment(JPasswordField.CENTER);


        labelLogin = new JLabel("LOGIN");
        labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogin.setBounds(300, 200, 400, 40);
        labelLogin.setFont(font);

        labelPassword = new JLabel("PASSWORD");
        labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
        labelPassword.setBounds(300, 270, 400, 40);
        labelPassword.setFont(font);

        buttonConnect = new JButton("CONNECT");
        buttonConnect.setBounds(400, 370, 200, 50);

        buttonConnect.addActionListener((ActionEvent e) -> {
            if(!loginText.getText().isEmpty()) {
                if (!this.client.setUsername(loginText.getText())) {
                    this.work = false;
                    this.setVisible(false);
                    this.dispose();
                }
            }
        });

        this.setVisible(true);

        this.add(password);
        this.add(loginText);
        this.add(labelLogin);
        this.add(labelPassword);
        this.add(buttonConnect);

        this.setResizable(false);
        try {
            colorChange();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void colorChange() throws InterruptedException {

        byte color = 0;
        boolean colorPlusOne = true;
        while (work) {

            if (colorPlusOne) {
                color++;
            } else {
                color--;
            }
            if (color == 127) {
                colorPlusOne = false;
            }
            if (color == 0) {
                colorPlusOne = true;
            }
            Thread.sleep(300);
            this.getContentPane().setBackground(new Color(color, color + 100, color * 2));
        }
    }

    public Client getClient(){
        return this.client;
    }

    public boolean isWork(){
        return this.work;
    }
}
=======
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by dimal on 08.04.2017.
 */
public class LoginWindow extends Frame{

    private JTextField loginText;
    private JPasswordField password;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JButton buttonConnect;
    private Client client;
    private boolean work = true;

    public LoginWindow(Client client) {
        super("Login");

        this.client = client;

        Font font = new Font(null, Font.CENTER_BASELINE, 22);

        loginText = new JTextField(25);
        loginText.setBounds(300, 230, 400, 40);
        loginText.setHorizontalAlignment(JTextField.CENTER);
        loginText.setFont(font);


        password = new JPasswordField(25);
        password.setBounds(300, 300, 400, 40);
        password.setHorizontalAlignment(JPasswordField.CENTER);


        labelLogin = new JLabel("LOGIN");
        labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogin.setBounds(300, 200, 400, 40);
        labelLogin.setFont(font);

        labelPassword = new JLabel("PASSWORD");
        labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
        labelPassword.setBounds(300, 270, 400, 40);
        labelPassword.setFont(font);

        buttonConnect = new JButton("CONNECT");
        buttonConnect.setBounds(400, 370, 200, 50);

        buttonConnect.addActionListener((ActionEvent e) -> {
            if(!this.client.setUsername(loginText.getText())){
                this.work = false;
                this.setVisible(false);
                this.dispose();
            }
        });

        this.setVisible(true);

        this.add(password);
        this.add(loginText);
        this.add(labelLogin);
        this.add(labelPassword);
        this.add(buttonConnect);

        this.setResizable(false);
        try {
            colorChange();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void colorChange() throws InterruptedException {

        byte color = 0;
        boolean colorPlusOne = true;
        while (work) {

            if (colorPlusOne) {
                color++;
            } else {
                color--;
            }
            if (color == 127) {
                colorPlusOne = false;
            }
            if (color == 0) {
                colorPlusOne = true;
            }
            Thread.sleep(300);
            this.getContentPane().setBackground(new Color(color, color + 100, color * 2));
        }
    }

    public Client getClient(){
        return this.client;
    }
}
>>>>>>> 0fa5fb1183fde38ee431f6524415ee608e3572bc
