import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Created by pavelgass.
 */
public abstract class Frame extends JFrame{

     public Frame() {



     super("WSIU CHAT");

         this.setLayout(null);
         this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
         this.getContentPane().setBackground(new Color(0, 182, 174));







         //Container container = this.getContentPane();
         //container.setLayout(new FlowLayout());

    }



}
