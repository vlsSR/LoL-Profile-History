package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {


    public Window() {
        initScreen();
    }

    public void initScreen() {
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        setVisible(true);
        setTitle("Plantilla");
    }

}
