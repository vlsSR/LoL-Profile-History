package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RiotAPIHandler {
    private final String API_KEY = APIKEY.MY_KEY;
    private final HttpClient client = HttpClient.newHttpClient();

    public String callApi(String url) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-Riot-Token", API_KEY)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Error: Código de estado " + response.statusCode());
                return String.valueOf(response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
