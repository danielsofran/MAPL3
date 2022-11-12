package service;

import domain.Prietenie;
import domain.User;
import domain.UserDetail;
import domain.UserDetails;
import domain.parser.Parser;
import exceptii.NotExistentException;
import repo.Repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ServiceUser {
    protected Repository<Long, User> repoUser;
    protected Repository<Long, Prietenie> repoPrietenii;
    protected Parser<Long> idParser;
    protected static Long idGenerator = 0L;

    /**
     * Constructorul clasei ServiceUser
     * initializeaza repository-urile, parserele si graful
     * @param repoUser - repository-ul de useri
     * @param repoPrietenii - repository-ul de prietenii
     * @param idParser - parser-ul de id-uri
     */
    public ServiceUser(Repository<Long, User> repoUser, Repository<Long, Prietenie> repoPrietenii, Parser<Long> idParser) {
        this.repoUser = repoUser;
        this.repoPrietenii = repoPrietenii;
        this.idParser = idParser;
        idGenerator = 1L;
        repoUser.findAll().stream().mapToLong(User::getId).max().ifPresent(mx -> idGenerator = mx + 1);
    }

    /**
     * adauda un user in repository si in graf
     * @param details - map cu cheia reprezentand un cod pentru un camp al user-ului
     *                - valoarea este valoarea campului
     */
    public void add(UserDetails details) {

        User user = new User(idGenerator, (String) details.get(UserDetail.Nume), (String) details.get(UserDetail.Email), (String) details.get(UserDetail.Password));
        repoUser.save(user);
        idGenerator++;
    }

    /**
     * sterge un user din repository si din graf
     * @param id - id-ul userului de sters
     */
    public void remove(Long id) {
        User user = repoUser.findOne(id);
        List<Prietenie> prietenii = new LinkedList<>(repoPrietenii.findAll());
        prietenii.forEach(prietenie -> {
            if (prietenie.getFirst().equals(user.getId()) || prietenie.getSecond().equals(user.getId()))
                repoPrietenii.delete(prietenie.getId());
        });
        repoUser.delete(id);
    }

    /**
     * modifica un user din repository si din graf
     * @param id - id-ul userului ale carui date le modificam
     * @param details - map cu cheia reprezentand un cod pentru un camp al user-ului
     *                - valoarea este valoarea campului
     */
    public void update(Long id, UserDetails details) {
        User newUser = new User(id, (String) details.get(UserDetail.Nume), (String) details.get(UserDetail.Email), (String) details.get(UserDetail.Password));
        repoUser.update(id, newUser);
    }

    /**
     * gaseste un user dupa id
     * @param id - id-ul user-ului de gasit
     * @return - userul gasit
     * @throws NotExistentException - daca nu exista userul
     */
    public User findOne(Long id) {
        User user = repoUser.findOne(id);
        if(user == null)
            throw new NotExistentException("Userul nu exista!");
        return user;
    }

    /**
     * @return toti userii din repository
     */
    public Collection<User> findAll() {
        return repoUser.findAll();
    }
}
