package controller;

import domain.Prietenie;
import domain.User;
import domain.parser.IdParser;
import domain.parser.Parser;
import domain.validation.PrietenieValidator;
import domain.validation.UserValidator;
import domain.validation.Validator;
import repo.InMemoryRepository;
import repo.Repository;
import service.ServicePrietenii;
import service.ServiceUser;

public class Controller {
    private final ServiceUser userService;
    private final ServicePrietenii prietenieService;

    /**
     * Constructorul clasei Service
     * initializeaza parserele, validatoarele, repository-urile, graful si serviciile
     */
    public Controller(){
        Parser<Long> idParser = new IdParser();
        Validator<User> validatorUser = new UserValidator();
        Validator<Prietenie> validatorPrietenie = new PrietenieValidator();
        Repository<Long, User> repoUser = new InMemoryRepository<>(validatorUser);
        Repository<Long, Prietenie> repoPrietenii = new InMemoryRepository<>(validatorPrietenie);
        userService = new ServiceUser(repoUser, repoPrietenii, idParser);
        prietenieService = new ServicePrietenii(repoPrietenii, repoUser, idParser);
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
}
