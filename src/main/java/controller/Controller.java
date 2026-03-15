package controller;

import model.*;

import view.Profile;
import view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.IllegalFormatException;
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
                    Summoner summoner = null;
                    try {
                        summoner = generateSummoner(puuid, parts);
                    } catch (com.google.gson.JsonSyntaxException ex) {
                        JOptionPane.showMessageDialog(view, "Your user is too old");
                        return;
                    }
                    Profile profile = new Profile();
                    loadDetails(profile, summoner);
                    if (summoner.getLeagueCount() == 1) {
                        if (summoner.getFlexQ() == null) {
                            loadSoloq(profile, summoner);
                        } else {
                            loadFlexq(profile, summoner);
                        }
                    } else if (summoner.getLeagueCount() == 2) {
                        loadSoloq(profile, summoner);
                        loadFlexq(profile, summoner);
                    }

                }
            }
        }
    }

    private static void loadDetails(Profile profile, Summoner summoner) {
        profile.getPlayerIcon().setImage(profile.getScaledIcon("/icons/" + summoner.getDetails().getProfileIconId() + ".png", 200, 200).getImage());
        profile.getSummonerName().setText(summoner.getSummonerName());
        profile.getSummonerLevel().setText(String.valueOf(summoner.getDetails().getSummonerLevel()));
    }

    private void loadFlexq(Profile profile, Summoner summoner) {
        profile.getFlexqIcon().setImage(profile.getScaledIcon("/ranks/"+tierConvertor(summoner.getFlexQ().getTier()), 400, 200).getImage());
        profile.getFlexqRank().setText(summoner.getFlexQ().getTier()+" "+ summoner.getFlexQ().getRank());
        profile.getFlexqResults().setText("W: "+ summoner.getFlexQ().getWins()+" L: "+ summoner.getFlexQ().getLosses());
        double winrateflexq = (double) summoner.getFlexQ().getWins() /(summoner.getFlexQ().getWins()+ summoner.getFlexQ().getLosses())*100;
        profile.getFlexqWinrate().setText("WR: "+Math.round(winrateflexq)+"%");
    }

    private void loadSoloq(Profile profile, Summoner summoner) {
        profile.getSoloqIcon().setImage(profile.getScaledIcon("/ranks/"+tierConvertor(summoner.getSoloQ().getTier()), 400, 200).getImage());
        profile.getSoloqRank().setText(summoner.getSoloQ().getTier()+" "+ summoner.getSoloQ().getRank());
        profile.getSoloqResults().setText("W: "+ summoner.getSoloQ().getWins()+" L: "+ summoner.getSoloQ().getLosses());
        double winratesoloq = (double) summoner.getSoloQ().getWins() /(summoner.getSoloQ().getWins()+ summoner.getSoloQ().getLosses())*100;
        profile.getSoloqWinrate().setText("WR: "+Math.round(winratesoloq)+"%");
    }

    private Summoner generateSummoner(String puuid, String[] parts) throws com.google.gson.JsonSyntaxException {
        Summoner summoner;
        SummonerDetail detail = null;

        detail = caller.getSummonerDetails(puuid);

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
        switch (tier.toLowerCase()) {
            case "iron":
                return "emblem-iron.png";
            case "bronze":
                return "emblem-bronze.png";
            case "silver":
                return "emblem-silver.png";
            case "gold":
                return "emblem-gold.png";
            case "platinum":
                return "emblem-platinum.png";
            case "emerald":
                return "emblem-emerald.png";
            case "diamond":
                return "emblem-diamond.png";
            case "master":
                return "emblem-master.png";
            case "grandmaster":
                return "emblem-grandmaster.png";
            case "challenger":
                return "emblem-challenger.png";
            default:
                return "default.png";
        }
    }
}
