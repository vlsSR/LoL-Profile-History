package controller;

import model.*;

import view.Profile;
import view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller implements ActionListener {

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
            if (view.getJTsummoner().getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Introduce a valid username");
            } else {
                String summonerInput = view.getJTsummoner().getText();
                String[] parts = parseSummonerName(summonerInput);
                String puuid = caller.getPlayerUUID(parts[0], parts[1]);
                if (puuid.equals("404")) {
                    JOptionPane.showMessageDialog(view, "Your user does not exists");
                } else {
                    try {
                        Summoner summoner = generateSummoner(puuid, parts);
                        Profile profile = new Profile();

                        profile.getPlayerIcon().setImage(profile.getScaledIcon("/icons/" + summoner.getDetails().getProfileIconId() + ".png", 200, 200).getImage());
                        profile.getSummonerName().setText(summoner.getSummonerName());
                        profile.getSummonerLevel().setText(String.valueOf(summoner.getDetails().getSummonerLevel()));
                    } catch (com.google.gson.JsonSyntaxException ex) {
                        JOptionPane.showMessageDialog(view, "Your user is too old");
                    }

                }
            }
        }
    }

    private Summoner generateSummoner(String puuid, String[] parts) throws com.google.gson.JsonSyntaxException{
        Summoner summoner;
        SummonerDetail detail = null;

        detail = caller.getSummonerDetails(puuid);

        JOptionPane.showMessageDialog(view, "Your user is too old");


        List<League> leagues = caller.getSummonerLeague(puuid);


        if (leagues.isEmpty()) {
            summoner = new Summoner(parts[0], detail);
        } else if (leagues.size() == 1) {
            summoner = new Summoner(parts[0], detail, leagues.getFirst());
        } else {
            summoner = new Summoner(parts[0], detail, leagues.getFirst(), leagues.getLast());
        }

        return summoner;
    }

    public String[] parseSummonerName(String summonerInput) {
        String[] parts = summonerInput.split("#");
        parts[0] = parts[0].trim();
        parts[0] = parts[0].replace(" ", "%20");
        return parts;
    }

    public String tierConvertor(String tier) {
        switch (tier) {
            //if (tier.equals())
        }
        return null;
    }
}
