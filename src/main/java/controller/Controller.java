package controller;

import model.APICalls;

import model.RiotAPIHandler;
import view.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

    private RiotAPIHandler handler;
    private APICalls caller;

    private Window view;

    public Controller(Window view) {
        this.view = view;
        handler = new RiotAPIHandler();
        caller = new APICalls(handler);

        view.getJBsearch().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String summonerInput = view.getJTsummoner().getText();
        String[] parts = parseSummonerName(summonerInput);
        String puuid = caller.getPlayerUUID(parts[0], parts[1]);
        System.out.println(puuid);
        System.out.println(caller.getSummonerDetails(puuid));
    }

    public String[] parseSummonerName(String summonerInput) {
        String[] parts = summonerInput.split("#");
        parts[0] = parts[0].trim();
        parts[0] = parts[0].replace(" ", "%20");
        return parts;
    }
}
