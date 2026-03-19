package model;

import com.google.gson.Gson;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class APICalls {
    private final RiotAPIHandler handler;

    public APICalls(RiotAPIHandler handler) {
        this.handler = handler;
    }

    public String getPlayerUUID(String summonersName, String summonersTag) {
        String url = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/"
                + summonersName + "/" + summonersTag;

        String jsonResponse = handler.callApi(url);

        if (jsonResponse.equals("404")) return "404";

        int start = jsonResponse.indexOf("\"puuid\":\"") + 9;
        int end = jsonResponse.indexOf("\"", start);

        return jsonResponse.substring(start, end);

    }

    public SummonerDetail getSummonerDetails(String puuid) throws com.google.gson.JsonSyntaxException {
        String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid;

        String jsonResponse = handler.callApi(url);

        if (jsonResponse == null) return null;

        Gson gson = new Gson();

        return gson.fromJson(jsonResponse, SummonerDetail.class);

    }

    public List<League> getSummonerLeague(String puuid) {
        String url = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-puuid/" + puuid;

        String jsonResponse = handler.callApi(url);

        if (jsonResponse == null) return null;
        Gson gson = new Gson();
        League[] leagues = gson.fromJson(jsonResponse, League[].class);

        List<League> leaguesList = Arrays.asList(leagues);
        Collections.sort(leaguesList);

        return leaguesList;

    }
}
