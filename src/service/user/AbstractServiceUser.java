package service.user;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import graf.GrafListaAdiacenta;
import repo.Repository;
import service.ServiceCRUD;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractServiceUser implements ServiceCRUD<User> {
    protected GrafListaAdiacenta<User, Prietenie> graf;
    protected Repository<Long, User> repoUser;
    protected Repository<Long, Prietenie> repoPrietenii;
    protected Parser<User> userParser;

    @Override
    public void add(String[] strings) {
        User user = userParser.parse(strings);
        repoUser.save(user);
        graf.addNod(user);
    }

    @Override
    public void remove(String[] strings) {
        Long id = userParser.parseId(strings[0]);
        User user = repoUser.findOne(id);
        List<Prietenie> prietenii = new LinkedList<>(repoPrietenii.findAll());
        prietenii.forEach(prietenie -> {
            if (prietenie.getFirst().equals(user) || prietenie.getSecond().equals(user))
                repoPrietenii.delete(prietenie.getId());
        });
        repoUser.delete(id);
        graf.removeNod(user);
    }

    @Override
    public void update(String[] strings) {
        Long id = userParser.parseId(strings[0]);
        User oldUser = repoUser.findOne(id);
        User newUser = userParser.parse(Arrays.copyOfRange(strings, 1, strings.length));
        repoUser.update(id, newUser);
        graf.updateNod(oldUser, newUser);
    }

    @Override
    public User findOne(String[] strings) {
        Long id = userParser.parseId(strings[0]);
        return repoUser.findOne(id);
    }

    @Override
    public Collection<User> findAll() {
        return repoUser.findAll();
    }
}
