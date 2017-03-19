import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;


/**
 * Created by pavelgass on 18.03.17.
 */
public class AuthorizationView extends Frame {

    AuthorizationView(){

        setResizable(false);
        setBounds(0,0,700,500);
        setLocationRelativeTo(null);

        Font font = new Font(null, Font.CENTER_BASELINE,22);

        JTextField loginText = new JTextField(25);
        loginText.setBounds(150,130,400,40);
        loginText.setHorizontalAlignment(JTextField.CENTER);
        loginText.setFont(font);


        JPasswordField password = new JPasswordField(25);
        password.setBounds(150, 200, 400, 40);
        password.setHorizontalAlignment(JPasswordField.CENTER);


        JLabel labelLogin = new JLabel("LOGIN");
        labelLogin.setBounds(317, 100, 400, 40);
        labelLogin.setFont(font);

        JLabel labelPassword = new JLabel("PASSWORD");
        labelPassword.setBounds(295, 170, 400, 40);
        labelPassword.setFont(font);

        JButton buttonConnect = new JButton("CONNECT");
        buttonConnect.setBounds(250, 270, 200, 50);


        
        add(password);
        add(loginText);
        add(labelLogin);
        add(labelPassword);
        add(buttonConnect);
    }





    public void colorChange() throws InterruptedException {



        byte color = 0;
        boolean colorPlusOne = true;
        while(true){

            if(colorPlusOne){
                color++;
            }else color--;
            if(color == 127){
                colorPlusOne = false;
            }
            if(color == 0){
                colorPlusOne = true;
            }
            Thread.sleep(300);
            System.out.println(color);
            this.getContentPane().setBackground(new Color(color, color+100, color*2));

        }
    }
}
