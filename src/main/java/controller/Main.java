package controller;

import model.APICalls;
import model.RiotAPIHandler;

public class Main {
    public static void main(String[] args) {
        RiotAPIHandler handler = new RiotAPIHandler();
        APICalls caller = new APICalls(handler);
        String puuid = caller.getPlayerUUID("Edgedancer%20Kata", "vls");
        System.out.println(puuid);
        //Model model = new Model();
        //Window view = new Window();
        //Controller controller = new Controller(model, view);
    }
}
