package model;

public class APICalls {
    private final RiotAPIHandler handler;

    public APICalls (RiotAPIHandler handler){
        this.handler = handler;
    }

    public String getPlayerUUID (String summonersName, String summonersTag) {
        String url = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/"
                + summonersName + "/" + summonersTag;

        String jsonResponse = handler.callApi(url);

        if (jsonResponse != null) {
            int start = jsonResponse.indexOf("\"puuid\":\"") + 9;
            int end = jsonResponse.indexOf("\"", start);

            return jsonResponse.substring(start, end);
        }

        return null;
    }
}
