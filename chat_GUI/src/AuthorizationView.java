import javax.swing.*;
import java.awt.*;


/**
 * Created by pavelgass on 18.03.17.
 */
public class AuthorizationView extends Frame {

    AuthorizationView(){


        setResizable(false);
        setBounds(0,0,725,525);
        setLocationRelativeTo(null);
        JTextField loginText = new JTextField(25);
        loginText.setLayout(null);
        loginText.setBounds(50,50,300,40);
        Font font = new Font(null, Font.TYPE1_FONT,22);
        loginText.setFont(font);




        add(loginText);

    }
}
