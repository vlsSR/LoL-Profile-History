package controller;

import model.*;

import view.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        if (e.getSource().equals(view.getJBsearch())) {
            generateSummoner();
        }
    }

    private void generateSummoner() {
        String summonerInput = view.getJTsummoner().getText();
        Summoner summoner = new Summoner();

        String[] parts = parseSummonerName(summonerInput);
        String puuid = caller.getPlayerUUID(parts[0], parts[1]);

        SummonerDetail detail = caller.getSummonerDetails(puuid);

        List<League> leagues = caller.getSummonerLeague(puuid);

        if (leagues.isEmpty()) {
            summoner = new Summoner(parts[0], detail);
        } else if (leagues.size() == 1) {
            summoner = new Summoner(parts[0], detail, leagues.getFirst());
        } else {
            summoner = new Summoner(parts[0], detail, leagues.getFirst(), leagues.getLast());
        }

        System.out.println(summoner);
    }

    public String[] parseSummonerName(String summonerInput) {
        String[] parts = summonerInput.split("#");
        parts[0] = parts[0].trim();
        parts[0] = parts[0].replace(" ", "%20");
        return parts;
    }
}
