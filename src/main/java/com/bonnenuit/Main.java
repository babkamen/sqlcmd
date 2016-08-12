package com.bonnenuit;

import com.bonnenuit.controler.Controller;
import com.bonnenuit.model.DatabaseManager;
import com.bonnenuit.view.Console;
import com.bonnenuit.view.View;

/**
 * Created by Rostyslav on 09/08/16.
 */
public class Main {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new DatabaseManager();
        Controller controller = new Controller(view, manager,true);
        controller.controllerRunner();
    }
}
