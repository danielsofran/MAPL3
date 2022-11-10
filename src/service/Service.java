package service;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import domain.parser.PrietenieParser;
import domain.parser.UserParser;
import domain.validation.PrietenieValidator;
import domain.validation.UserValidator;
import domain.validation.Validator;
import graf.GrafListaAdiacenta;
import repo.Repository;
import repo.memory.InMemoryRepository;
import service.prietenii.ServicePrietenii;
import service.user.ServiceUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Service {

    private final ServiceUser userService;
    private final ServicePrietenii prietenieService;

    /**
     * Constructorul clasei Service
     * initializeaza parserele, validatoarele, repository-urile, graful si serviciile
     */
    public Service(){
        Parser<User> userParser = new UserParser();
        Parser<Prietenie> prietenieParser = new PrietenieParser();
        Validator<User> validatorUser = new UserValidator();
        Validator<Prietenie> validatorPrietenie = new PrietenieValidator();
        Repository<Long, User> repoUser = new InMemoryRepository<>(validatorUser);
        Repository<Long, Prietenie> repoPrietenii = new InMemoryRepository<>(validatorPrietenie);
        userService = new ServiceUser(repoUser, repoPrietenii, userParser);
        prietenieService = new ServicePrietenii(repoPrietenii, repoUser, prietenieParser);
        LoadFromFiles();
    }

    /**
     * @return serviciul de user
     */
    public ServiceUser getServiceUser(){
        return userService;
    }

    /**
     * @return serviciul de prietenie
     */
    public ServicePrietenii getServicePrietenii(){
        return prietenieService;
    }

    /**
     * incarca datele din fisiere
     * @param srv - service-ul in care se vor incarca datele
     * @param fileName - numele fisierului
     * @param <E> - tipul entitatilor
     * @throws RuntimeException - exceptie de la citirea din fisier
     */
    private<E> void LoadFromFile(ServiceCRUD<E> srv,String fileName)
    {
        Path path = Paths.get(fileName);

        try(Stream<String> lines = Files.lines(path)){
            lines.forEach(line -> {
                String[] tokens = line.split(";");
                srv.add(tokens);
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * incarca datele din fisiere
     */
    private void LoadFromFiles()
    {
        String data = Paths.get("./data").normalize().toAbsolutePath() + "\\";
        LoadFromFile(userService,data + "useri");
        LoadFromFile(prietenieService,data + "prietenii");
    }
}
