import domain.Entity;
import domain.User;
import domain.validation.Validator;
import service.Service;
import ui.console.UIConsole;

import java.util.*;

public class Main {
    /**
     * Functia main
     * @param args - argumentele
     */
    public static void main(String[] args) {
        Service service = new Service();
        UIConsole uiConsole = new UIConsole(service);
        uiConsole.run();
    }
}