package service.user;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import graf.GrafListaAdiacenta;
import repo.Repository;

public class ServiceUser extends AbstractServiceUser {
    public ServiceUser(Repository<Long, User> repository, Repository<Long, Prietenie> prietenieRepository, Parser<User> userParser, GrafListaAdiacenta<User, Prietenie> graf) {
        this.repoUser = repository;
        this.repoPrietenii = prietenieRepository;
        this.userParser = userParser;
        this.graf = graf;
    }
}
