package view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Profile extends JFrame{
    private ImageIcon playerIcon, soloqIcon, flexqIcon;
    private JLabel summonerName, summonerLevel, soloqRank, soloqResults, soloqWinrate, flexqRank, flexqResults, flexqWinrate, JLplayerIcon, JLsoloqIcon, JLflexqIcon, JLflexq, JLsoloq;
    private JPanel details, soloq, flexq;
    public Profile() {
        initDetails();
        initSoloq();
        initFlexq();
        initScreen();
    }

    public void initFlexq() {
        flexq = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLflexq = new JLabel("FlexQ");
        JLflexq.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 0;
        flexq.add(JLflexq, gbc);

        flexqIcon = getScaledIcon("/ranks/emblem-iron.png", 400, 200);
        JLflexqIcon = new JLabel(flexqIcon);
        gbc.gridy = 1;
        flexq.add(JLflexqIcon, gbc);

        flexqRank = new JLabel("Unranked");
        gbc.gridy = 2;
        flexq.add(flexqRank, gbc);

        flexqResults = new JLabel("W: 0 L: 0");
        gbc.gridy = 3;
        flexq.add(flexqResults, gbc);

        flexqWinrate = new JLabel("WR: ?");
        gbc.gridy = 4;
        flexq.add(flexqWinrate, gbc);
        flexq.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        flexq.setBackground(Color.WHITE);
    }

    public void initSoloq() {
        soloq = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLsoloq = new JLabel("SoloQ");
        JLsoloq.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 0;
        soloq.add(JLsoloq, gbc);

        soloqIcon = getScaledIcon("/ranks/emblem-iron.png", 400, 200);
        JLsoloqIcon = new JLabel(soloqIcon);
        gbc.gridy = 1;
        soloq.add(JLsoloqIcon, gbc);

        soloqRank = new JLabel("Unranked");
        gbc.gridy = 2;
        soloq.add(soloqRank, gbc);

        soloqResults = new JLabel("W: 0 L: 0");
        gbc.gridy = 3;
        soloq.add(soloqResults, gbc);

        soloqWinrate = new JLabel("WR: ?");
        gbc.gridy = 4;
        soloq.add(soloqWinrate, gbc);
        soloq.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        soloq.setBackground(Color.WHITE);
    }

    public void initDetails() {
        details = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.gridx = 0;

        playerIcon = getScaledIcon("/icons/1.png", 120, 120);
        JLplayerIcon = new JLabel(playerIcon);
        gbc.gridy = 0;
        details.add(JLplayerIcon, gbc);

        summonerName = new JLabel("PlaceHolder");
        summonerName.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 1;
        details.add(summonerName, gbc);

        summonerLevel = new JLabel("Nivel: 1234");
        summonerLevel.setForeground(Color.GRAY);
        gbc.gridy = 2;
        details.add(summonerLevel, gbc);
    }

    public void initScreen() {
        setSize(1000, 700);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        setTitle("Profile");

        add(details, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        centerPanel.add(soloq);
        centerPanel.add(flexq);
        add(centerPanel, BorderLayout.CENTER);
    }


    public ImageIcon getScaledIcon(String path, int width, int height) {
        URL url = getClass().getResource(path);
        if (url == null) return null;
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public ImageIcon getPlayerIcon() {
        return playerIcon;
    }


    public ImageIcon getSoloqIcon() {
        return soloqIcon;
    }


    public ImageIcon getFlexqIcon() {
        return flexqIcon;
    }


    public JLabel getSummonerName() {
        return summonerName;
    }


    public JLabel getSummonerLevel() {
        return summonerLevel;
    }


    public JLabel getSoloqRank() {
        return soloqRank;
    }


    public JLabel getSoloqResults() {
        return soloqResults;
    }


    public JLabel getSoloqWinrate() {
        return soloqWinrate;
    }


    public JLabel getFlexqRank() {
        return flexqRank;
    }


    public JLabel getFlexqResults() {
        return flexqResults;
    }


    public JLabel getFlexqWinrate() {
        return flexqWinrate;
    }

}
