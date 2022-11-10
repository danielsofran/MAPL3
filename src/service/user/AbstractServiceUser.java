package service.user;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import exceptii.NotExistentException;
import repo.Repository;
import service.ServiceCRUD;

import java.util.*;

public abstract class AbstractServiceUser implements ServiceCRUD<User> {
    protected Repository<Long, User> repoUser;
    protected Repository<Long, Prietenie> repoPrietenii;
    protected Parser<User> userParser;
    protected static Long idGenerator = 0L;

    /**
     * adauda un user in repository si in graf
     * @param strings - elementul de adaugat
     */
    @Override
    public void add(String[] strings) {
        String sid = idGenerator.toString();
        String[] newstrings = new String[strings.length + 1];
        newstrings[0] = sid;
        System.arraycopy(strings, 0, newstrings, 1, strings.length);
        User user = userParser.parse(newstrings);
        repoUser.save(user);
        idGenerator++;
    }

    /**
     * sterge un user din repository si din graf
     * @param strings - userul de sters
     */
    @Override
    public void remove(String[] strings) {
        Long id = userParser.parseId(strings[0]);
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
     * @param strings - userul de modificat
     */
    @Override
    public void update(String[] strings) {
        Long id = userParser.parseId(strings[0]);
        User newUser = userParser.parse(strings);
        repoUser.update(id, newUser);
    }

    /**
     * gaseste un user dupa id
     * @param strings - elementul de verificat
     * @return - userul gasit
     * @throws NotExistentException - daca nu exista userul
     */
    @Override
    public User findOne(String[] strings) {
        Long id = userParser.parseId(strings[0]);
        User user = repoUser.findOne(id);
        if(user == null)
            throw new NotExistentException("Userul nu exista!");
        return user;
    }

    /**
     * @return toti userii din repository
     */
    @Override
    public Collection<User> findAll() {
        return repoUser.findAll();
    }
}
