package ui.console;

import domain.Prietenie;
import domain.User;
import exceptii.MyException;
import service.Service;
import utils.Pair;
import utils.Utils;

import java.util.Scanner;
import java.util.Set;

public class UIPrietenii {
    private static final String COMMAND = "prietenii";
    private Scanner scanner;
    private Service service;

    /**
     * Constructor pentru interfata prietenilor, seteaza service-ul si scanner-ul
     * @param service - serviciul
     * @param scanner - scannerul
     */
    public UIPrietenii(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Executa comanda asociata argumentelor
     * @param args - argumentele comenzii
     */
    public void execute(String[] args) {
        if(args.length == 1){
            System.out.println("Invalid command! Try one of the following:");
            System.out.println("add, remove, update, find, findall");
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
            case "findAll":
                findAll();
                break;
            case "nrcomunitati":
                numarComunitati();
                break;
            case "cmsc":
                ceaMaiSociabilaComunitate();
                break;
            default:
                System.out.println("Invalid command! Try one of the following:");
                System.out.println("add, remove, update, find, findAll, nrcomunitati, cmsc");
        }
    }

    /**
     * Cea mai sociabila comunitate
     */
    private void ceaMaiSociabilaComunitate() {
        Utils.tryExecute(() -> {
            Pair<Set<User>, Integer> com = service.getServicePrietenii().getCeaMaiSociabilaComunitate();
            System.out.println("Cea mai sociabila comunitate are scorul "+com.second+" si este formata din:");
            com.first.forEach(System.out::println);
        });
    }

    /**
     * Numarul de comunitati
     */
    private void numarComunitati() {
        Utils.tryExecute(() -> {
            System.out.println("Numarul de comunitati este: " + service.getServicePrietenii().getNumarComunitati());
        });
    }

    /**
     * Afiseaza toate prietenii
     */
    private void findAll() {
        System.out.println("Prietenii:");
        Utils.tryExecute(() -> service.getServicePrietenii().findAll().forEach(System.out::println));
    }

    /**
     * Afiseaza o prietenie
     */
    private void findPrietenie() {
        String[] params = new String[1];
        System.out.print("Id: ");
        params[0] = scanner.nextLine().trim();
        Utils.tryExecute(() -> {
            Prietenie prietenie = service.getServicePrietenii().findOne(params);
            System.out.println(prietenie);
        });
    }

    /**
     * Actualizeaza o prietenie
     */
    private void updatePrietenie() {
        String[] params = new String[4];
        System.out.print("Old Id: ");
        params[0] = scanner.nextLine().trim();
        System.out.print("Id: ");
        params[1] = scanner.nextLine().trim();
        System.out.print("Id User 1: ");
        params[2] = scanner.nextLine().trim();
        System.out.print("Id User 2: ");
        params[3] = scanner.nextLine().trim();
        Utils.tryExecute(() -> service.getServicePrietenii().add(params));
    }

    /**
     * Sterge o prietenie
     */
    private void removePrietenie() {
        String[] params = new String[1];
        System.out.print("Id: ");
        params[0] = scanner.nextLine().trim();
        Utils.tryExecute(() -> service.getServicePrietenii().remove(params));
    }

    /**
     * Adauga o prietenie
     */
    private void addPrietenie() {
        String[] params = new String[3];
        System.out.print("Id: ");
        params[0] = scanner.nextLine().trim();
        System.out.print("Id User 1: ");
        params[1] = scanner.nextLine().trim();
        System.out.print("Id User 2: ");
        params[2] = scanner.nextLine().trim();
        Utils.tryExecute(() -> service.getServicePrietenii().add(params));
    }
}
