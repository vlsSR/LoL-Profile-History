package controller;

import model.*;

import view.Profile;
import view.Window;

import javax.swing.*;
import java.util.List;

public class Controller {

    private final APICalls caller;
    private final Window view;

    public Controller(Window view) {
        this.view = view;
        RiotAPIHandler handler = new RiotAPIHandler();
        caller = new APICalls(handler);

        view.getJBsearch().addActionListener(e -> actionPerformed());
    }

    public void actionPerformed() {
        String[] parts;
        String puuid;
        Summoner summoner;
        String searchInput = view.getJTsummoner().getText();

        if (view.getJTsummoner().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Username Empty");
            return;
        }

        try {
             parts = parseSummonerName(searchInput);
             puuid = caller.getPlayerUUID(parts[0], parts[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(view, "Invalid username format (user#tag)");
            return;
        }


        if (puuid.equals("404")) {
            JOptionPane.showMessageDialog(view, "Your user does not exists");
            return;
        }

        try {
            summoner = generateSummoner(puuid, parts);
        } catch (com.google.gson.JsonSyntaxException ex) {
            JOptionPane.showMessageDialog(view, "Your user is too old");
            return;
        }

        loadProfile(summoner);

    }

    private void loadProfile(Summoner summoner) {
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

    private static void loadDetails(Profile profile, Summoner summoner) {
        profile.getPlayerIcon()
                .setImage(profile.getScaledIcon(
                        "/icons/" +
                                summoner.getDetails().getProfileIconId() +
                                ".png", 200, 200).getImage());
        profile.getSummonerName()
                .setText(summoner.getSummonerName());
        profile.getSummonerLevel()
                .setText(String.valueOf(
                        summoner.getDetails().getSummonerLevel()));
    }

    private void loadFlexq(Profile profile, Summoner summoner) {
        profile.getFlexqIcon()
                .setImage(profile.getScaledIcon(
                                "/ranks/" + tierConvertor(summoner.getFlexQ().getTier()), 400, 200)
                        .getImage());
        profile.getFlexqRank()
                .setText(summoner.getFlexQ().getTier() +
                        " " +
                        summoner.getFlexQ().getRank());
        profile.getFlexqResults()
                .setText("W: " + summoner.getFlexQ().getWins() +
                        " L: " + summoner.getFlexQ().getLosses());
        double wins = summoner.getFlexQ().getWins();
        double losses = summoner.getFlexQ().getLosses();
        double winrateflexq = wins / (wins + losses) * 100;
        profile.getFlexqWinrate().setText("WR: " + Math.round(winrateflexq) + "%");
    }

    private void loadSoloq(Profile profile, Summoner summoner) {
        profile.getSoloqIcon()
                .setImage(
                        profile.getScaledIcon(
                                        "/ranks/" + tierConvertor(summoner.getSoloQ().getTier()), 400, 200)
                                .getImage()
                );
        profile.getSoloqRank()
                .setText(summoner.getSoloQ().getTier() +
                        " " +
                        summoner.getSoloQ().getRank());
        profile.getSoloqResults()
                .setText("W: " + summoner.getSoloQ().getWins() +
                        " L: " + summoner.getSoloQ().getLosses());
        double wins = summoner.getSoloQ().getWins();
        double losses = summoner.getSoloQ().getLosses();
        double winratesoloq = wins / (wins + losses) * 100;
        profile.getSoloqWinrate().setText("WR: " + Math.round(winratesoloq) + "%");
    }

    private Summoner generateSummoner(String puuid, String[] parts) throws com.google.gson.JsonSyntaxException {
        Summoner summoner;
        SummonerDetail detail;

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
        return "emblem-" + tier.toLowerCase() + ".png";
    }
}
