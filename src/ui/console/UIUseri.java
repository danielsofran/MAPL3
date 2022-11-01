package ui.console;

import domain.User;
import service.Service;
import utils.Utils;

import java.util.Scanner;

public class UIUseri {
    private static final String COMMAND = "prietenii";
    private Scanner scanner;
    private Service service;

    /**
     * Constructor pentru interfata user-ilor, seteaza service-ul si scanner-ul
     * @param service - serviciul
     * @param scanner - scannerul
     */
    public UIUseri(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Executa comanda asociata argumentelor
     * @param args - argumentele comenzii
     */
    public void execute(String[] args) {
        switch (args[1]){
            case "add":
                addUser();
                break;
            case "remove":
                removeUser();
                break;
            case "update":
                updateUser();
                break;
            case "find":
                findUser();
                break;
            case "findall":
                findAll();
                break;
            default:
                System.out.println("Invalid command! Try one of the following:");
                System.out.println("add, remove, update, find, findall");
        }
    }

    /**
     * Afiseaza toti userii
     */
    private void findAll() {
        Utils.tryExecute(() -> service.getServiceUser().findAll().forEach(System.out::println));
    }

    /**
     * Afiseaza user-ul cu id-ul dat
     */
    private void findUser() {
        String[] params = new String[1];
        System.out.print("Introduceti id-ul user-ului cautat: ");
        params[0] = scanner.nextLine();
        Utils.tryExecute(() -> {
            User user = service.getServiceUser().findOne(params);
            System.out.println(user);
        });
    }

    /**
     * Actualizeaza user-ul cu id-ul dat
     */
    private void updateUser() {
        String[] params = new String[4];
        System.out.print("Introduceti id-ul user-ului pe care doriti sa il modificati: ");
        params[0] = scanner.nextLine();
        System.out.print("Introduceti numele user-ului: ");
        params[1] = scanner.nextLine();
        System.out.print("Introduceti emailul user-ului: ");
        params[2] = scanner.nextLine();
        System.out.print("Introduceti parola user-ului: ");
        params[3] = scanner.nextLine();
        Utils.tryExecute(() -> {
            service.getServiceUser().update(params);
        });
    }

    /**
     * Sterge user-ul cu id-ul dat
     */
    private void removeUser() {
        String[] params = new String[1];
        System.out.print("Introduceti id-ul user-ului pe care doriti sa il stergeti: ");
        params[0] = scanner.nextLine();
        Utils.tryExecute(() -> {
            service.getServiceUser().remove(params);
        });
    }

    /**
     * Adauga un user
     */
    private void addUser() {
        String[] params = new String[4];
        System.out.print("Id: ");
        params[0] = scanner.nextLine().trim();
        System.out.print("Nume: ");
        params[1] = scanner.nextLine().trim();
        System.out.print("Email: ");
        params[2] = scanner.nextLine().trim();
        System.out.print("Password: ");
        params[3] = scanner.nextLine().trim();
        Utils.tryExecute(() -> service.getServiceUser().add(params));
    }
}
