package controller;

import view.Window;

public class Main {
    public static void main(String[] args) {

        Window view = new Window();
        Controller controller = new Controller(view);
    }
}
