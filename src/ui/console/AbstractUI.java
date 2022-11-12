package ui.console;

import controller.Controller;
import domain.parser.Parser;
import exceptii.ParsingException;

import java.util.Scanner;

public abstract class AbstractUI {
    protected final Scanner scanner;
    protected final Controller controller;
    protected final Parser<Long> idParser;

    /**
     * Constructor pentru interfata user-ilor, seteaza service-ul si scanner-ul
     * @param controller - controller-ul
     * @param scanner - scannerul
     */
    public AbstractUI(Controller controller, Scanner scanner, Parser<Long> parser) {
        this.controller = controller;
        this.scanner = scanner;
        this.idParser = parser;
    }

    /**
     * citeste de la tastatura si parseaza un id
     * @param msg - mesajul afisat
     * @return - id-ul parsat
     * @throws ParsingException - daca utilizatorul nu introduce date valide
     */
    protected Long readId(String msg) throws ParsingException {
        if(msg == null) msg = "Introduceti id-ul";
        System.out.print(msg + " ");
        String[] sid = new String[1];
        sid[0] = scanner.nextLine();
        return idParser.parse(sid);
    }

    /**
     * Executa comanda asociata argumentelor
     * @param args - argumentele comenzii
     */
    public abstract void execute(String[] args);
}
