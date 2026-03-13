package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JButton JBsearch;
    private JTextField JTsummoner;
    private JPanel JPsearch;

    public Window() {
        initSearch();
        initScreen();
    }

    public void initSearch() {
        JPsearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTsummoner = new JTextField(20);
        JBsearch = new JButton("Buscar");

        JPsearch.add(JTsummoner);
        JPsearch.add(JBsearch);
    }

    public void initScreen() {
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        setVisible(true);
        setTitle("Plantilla");

        this.add(JPsearch);
    }

    public JButton getJBsearch() {
        return JBsearch;
    }

    public JTextField getJTsummoner() {
        return JTsummoner;
    }
}
