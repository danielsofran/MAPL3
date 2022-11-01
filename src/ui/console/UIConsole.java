package ui.console;

import service.Service;
import ui.UI;

import java.util.Scanner;

public class UIConsole implements UI {

    private UIPrietenii uiPrietenii;
    private UIUseri uiUseri;
    private Service service;
    private Scanner scanner;

    /**
     * Constructor
     * @param service - serviciul
     */
    public UIConsole(Service service){
        this.service = service;
        scanner = new Scanner(System.in);
        uiPrietenii = new UIPrietenii(service, scanner);
        uiUseri = new UIUseri(service, scanner);
    }

    /**
     * Ruleaza interfata grafica
     */
    public void run() {
        while (true){
            String[] args = scanner.nextLine().split(" ");
            execute(args);
        }
    }

    /**
     * Executa comanda
     * @param args - argumentele comenzii
     */
    private void execute(String[] args) {
        switch (args[0]){
            case "prietenii":
                uiPrietenii.execute(args);
                break;
            case "useri":
                uiUseri.execute(args);
                break;
            default:
                System.out.println("Invalid command! Try one of the following:");
                System.out.println("prietenii, useri");
        }
    }
}
