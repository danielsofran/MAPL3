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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Service {
    private final Parser<User> userParser;
    private final Parser<Prietenie> prietenieParser;

    private ServiceUser userService;
    private ServicePrietenii prietenieService;

    /**
     * Constructorul clasei Service
     * initializeaza parserele, validatoarele, repository-urile, graful si serviciile
     */
    public Service(){
        userParser = new UserParser();
        prietenieParser = new PrietenieParser();
        Validator<User> validatorUser = new UserValidator();
        Validator<Prietenie> validatorPrietenie = new PrietenieValidator();
        Repository<Long, User> repoUser = new InMemoryRepository<>(validatorUser);
        Repository<Long, Prietenie> repoPrietenii = new InMemoryRepository<>(validatorPrietenie);
        GrafListaAdiacenta<User, Prietenie> grafPrietenii = new GrafListaAdiacenta<>();
        userService = new ServiceUser(repoUser, repoPrietenii, userParser, grafPrietenii);
        prietenieService = new ServicePrietenii(repoPrietenii, repoUser, prietenieParser, grafPrietenii);
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

    private<E> void LoadFromFile(ServiceCRUD<E> srv,String fileName)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while((linie=br.readLine())!=null){
                String[] attr = linie.split(";");
                srv.add(attr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadFromFiles()
    {
        LoadFromFile(userService,"C:\\Users\\Demian\\Desktop\\L3\\data\\useri");
        LoadFromFile(prietenieService,"C:\\Users\\Demian\\Desktop\\L3\\data\\prietenii");
    }
}
