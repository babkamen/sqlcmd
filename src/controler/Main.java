package controler;

import model.DatabaseManager;
import view.Console;
import view.View;

/**
 * Created by Rostyslav on 09/08/16.
 */
public class Main {
    public static void main(String[] args) {


        View view = new Console();
        DatabaseManager manager = new DatabaseManager();
        Controller controller = new Controller(view, manager);
        controller.controllerRunner();
    }
}
