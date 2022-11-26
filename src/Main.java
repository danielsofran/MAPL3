import controller.Controller;
import controller.ControllerOption;
import domain.Entity;
import domain.User;
import domain.validation.Validator;
import ui.console.UIConsole;

import java.util.*;

public class Main {
    /**
     * Functia main
     * @param args - argumentele
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        UIConsole uiConsole = new UIConsole(controller);
        uiConsole.run();
    }
}