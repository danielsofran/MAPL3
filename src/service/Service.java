package service;

import domain.Prietenie;
import domain.User;
import domain.validation.PrietenieValidator;
import domain.validation.UserValidator;
import domain.validation.Validator;
import repo.Repository;
import repo.memory.InMemoryRepository;

public class Service {
    private Repository<Long, User> repoUser;
    private Repository<Long, Prietenie> repoPrietenie;

    public Service(){
        Validator<User> validatorUser = new UserValidator();
        Validator<Prietenie> validatorPrietenie = new PrietenieValidator();
        repoUser = new InMemoryRepository<>(validatorUser);
        repoPrietenie = new InMemoryRepository<>(validatorPrietenie);
    }

}
