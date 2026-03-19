package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RiotAPIHandler {

    public String callApi(String url) {
        HttpClient client = HttpClient.newHttpClient();

        String API_KEY = APIKEY.MY_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-Riot-Token", API_KEY)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error: Código de estado " + response.statusCode());
                return String.valueOf(response.statusCode());
            }

            return response.body();

        } catch (Exception e) {
            return null;
        }
    }
}
