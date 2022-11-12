package ui.console;

import controller.Controller;
import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import graf.StrategiiCelMaiLungDrum;
import utils.Pair;
import utils.Utils;

import java.util.Scanner;
import java.util.Set;

public class UIPrietenii extends AbstractUI {
    /**
     * Constructor pentru interfata prietenilor, seteaza service-ul si scanner-ul
     * @param controller - controller-ul
     * @param scanner - scannerul
     */
    public UIPrietenii(Controller controller, Scanner scanner, Parser<Long> parser) {
        super(controller, scanner, parser);
    }

    /**
     * Executa comanda asociata argumentelor
     * @param args - argumentele comenzii
     */
    @Override
    public void execute(String[] args) {
        Runnable errorMsg = () -> {
            System.out.println("Invalid subcommand! Try one of the following:");
            System.out.println("add, remove, update, find, findall, nrcomunitati, cmsc");
        };
        if(args.length < 2){
            errorMsg.run();
            return;
        }
        switch (args[1]){
            case "add":
                addPrietenie();
                break;
            case "remove":
                removePrietenie();
                break;
            case "update":
                updatePrietenie();
                break;
            case "find":
                findPrietenie();
                break;
            case "findall":
                findAll();
                break;
            case "nrcomunitati":
                numarComunitati();
                break;
            case "cmsc":
                ceaMaiSociabilaComunitate();
                break;
            default:
                errorMsg.run();
        }
    }

    /**
     * Cea mai sociabila comunitate
     */
    private void ceaMaiSociabilaComunitate() {
        Utils.tryExecute(() -> {
            Pair<Set<User>, Integer> com = controller.getServicePrietenii().getCeaMaiSociabilaComunitate(StrategiiCelMaiLungDrum.Backtracking);
            System.out.println("Cea mai sociabila comunitate are scorul "+com.getSecond()+" si este formata din:");
            com.getFirst().forEach(System.out::println);
        });
    }

    /**
     * Numarul de comunitati
     */
    private void numarComunitati() {
        Utils.tryExecute(() ->
            System.out.println("Numarul de comunitati este: " + controller.getServicePrietenii().getNumarComunitati())
        );
    }

    /**
     * Afiseaza toate prietenii
     */
    private void findAll() {
        if(!controller.getServicePrietenii().findAll().isEmpty()) {
            System.out.println("Prietenii:");
            Utils.tryExecute(() -> controller.getServicePrietenii().findAll().forEach(System.out::println));
        }
        else {
            System.err.println("Nu exista nici o prietenie!");
        }
    }

    /**
     * Afiseaza o prietenie
     */
    private void findPrietenie() {
        Utils.tryExecute(() -> {
            Long id = readId("Id-ul prieteniei:");
            Prietenie prietenie = controller.getServicePrietenii().findOne(id);
            System.out.println(prietenie);
        });
    }

    /**
     * Actualizeaza o prietenie
     */
    private void updatePrietenie() {
        Utils.tryExecute(() -> {
            Long id = readId("Id prietenie:");
            Long id1 = readId("Id user 1:");
            Long id2 = readId("Id user 2:");
            controller.getServicePrietenii().update(id, id1, id2);
        });
    }

    /**
     * Sterge o prietenie
     */
    private void removePrietenie() {
        Utils.tryExecute(() -> {
            Long id = readId(null);
            controller.getServicePrietenii().remove(id);
        });
    }

    /**
     * Adauga o prietenie
     */
    private void addPrietenie() {
        Utils.tryExecute(() -> {
            Long id1 = readId("Id user 1:");
            Long id2 = readId("Id user 2:");
            controller.getServicePrietenii().add(id1, id2);
        });
    }
}
