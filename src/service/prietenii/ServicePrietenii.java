package service.prietenii;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import graf.GrafListaAdiacenta;
import repo.Repository;
import utils.Pair;

public class ServicePrietenii extends AbstractServicePrietenii {
    public ServicePrietenii(Repository<Long, Prietenie> repository, Repository<Long, User> repoUser, Parser<Prietenie> prietenieParser, GrafListaAdiacenta<User, Prietenie> graf) {
        this.repoPrietenii = repository;
        this.repoUser = repoUser;
        this.graf = graf;
        this.parserPrietenie = prietenieParser;
    }
}
