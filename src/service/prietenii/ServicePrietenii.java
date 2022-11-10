package service.prietenii;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import graf.GrafListaAdiacenta;
import repo.Repository;
import service.ServiceCRUD;
import utils.Pair;

public class ServicePrietenii extends AbstractServicePrietenii {

    /**
     * Constructorul clasei ServicePrietenii
     * initializeaza repository-urile, parserele si graful
     * @param repoPrietenii - repository-ul de prietenii
     * @param repoUser - repository-ul de useri
     * @param prietenieParser - parser-ul de prietenii
     */
    public ServicePrietenii(Repository<Long, Prietenie> repoPrietenii, Repository<Long, User> repoUser, Parser<Prietenie> prietenieParser) {
        this.repoPrietenii = repoPrietenii;
        this.repoUser = repoUser;
        this.parserPrietenie = prietenieParser;
    }
}
