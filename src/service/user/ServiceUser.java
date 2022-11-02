package service.user;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import graf.GrafListaAdiacenta;
import repo.Repository;
import service.ServiceCRUD;

public class ServiceUser extends AbstractServiceUser {
    /**
     * Constructorul clasei ServiceUser
     * initializeaza repository-urile, parserele si graful
     * @param repoUser - repository-ul de useri
     * @param repoPrietenii - repository-ul de prietenii
     * @param userParser - parser-ul de useri
     * @param graf - graful de prietenii
     */
    public ServiceUser(Repository<Long, User> repoUser, Repository<Long, Prietenie> repoPrietenii, Parser<User> userParser, GrafListaAdiacenta<User, Prietenie> graf) {
        this.repoUser = repoUser;
        this.repoPrietenii = repoPrietenii;
        this.userParser = userParser;
        this.graf = graf;
    }
}
