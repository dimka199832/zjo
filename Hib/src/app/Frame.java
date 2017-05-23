package app;

import javax.swing.*;

/**
 * Created by dimal on 27.03.2017.
 */
public class Frame extends JFrame {
    public Frame(String title){
        super(title);
        //this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        this.setLayout(null);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
