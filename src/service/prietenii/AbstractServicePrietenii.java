package service.prietenii;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import exceptii.DuplicatedElementException;
import exceptii.NotExistentException;
import graf.GrafListaAdiacenta;
import repo.Repository;
import service.ServiceCRUD;
import utils.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractServicePrietenii implements ServiceCRUD<Prietenie> {
    protected Repository<Long, User> repoUser;
    protected Repository<Long, Prietenie> repoPrietenii;
    protected Parser<Prietenie> parserPrietenie;
    protected GrafListaAdiacenta<User, Prietenie> graf;

    private Prietenie fromIds(String[] strings){
        Long id = parserPrietenie.parseId(strings[0]);
        Long id1 = parserPrietenie.parseId(strings[1]);
        Long id2 = parserPrietenie.parseId(strings[2]);
        User user1 = repoUser.findOne(id1);
        User user2 = repoUser.findOne(id2);
        return new Prietenie(id, user1, user2);
    }

    @Override
    public void add(String[] strings) {
        Prietenie prietenie = fromIds(strings);
        // find the users
        List<Prietenie> prietenii = new ArrayList<>(repoPrietenii.findAll());
        prietenii.forEach(prietenie1 -> {
            if (prietenie1.equals(prietenie))
                throw new DuplicatedElementException("Prietenia exista deja!");
        });
        repoPrietenii.save(prietenie);
        graf.addMuchie(prietenie);
    }

    @Override
    public void remove(String[] strings) {
        Long id = parserPrietenie.parseId(strings[0]);
        Prietenie prietenie = repoPrietenii.findOne(id);
        AtomicBoolean exists = new AtomicBoolean(false);
        List<Prietenie> prietenii = new ArrayList<>(repoPrietenii.findAll());
        prietenii.forEach(prietenie1 -> {
            if (prietenie1.equals(prietenie))
                exists.set(true);
        });
        if (!exists.get())
            throw new NotExistentException("Prietenia nu exista!");
        repoPrietenii.delete(id);
        graf.removeMuchie(prietenie);
    }

    @Override
    public void update(String[] strings) {
        Long id = parserPrietenie.parseId(strings[0]);
        Prietenie oldPrietenie = repoPrietenii.findOne(id);
        AtomicBoolean exists = new AtomicBoolean(false);
        List<Prietenie> prietenii = new ArrayList<>(repoPrietenii.findAll());
        prietenii.forEach(prietenie1 -> {
            if (prietenie1.equals(oldPrietenie))
                exists.set(true);
        });
        if (!exists.get())
            throw new NotExistentException("Prietenia nu exista!");
        Prietenie newPrietenie = fromIds(Arrays.copyOfRange(strings, 1, strings.length));
        repoPrietenii.update(id, newPrietenie);
        graf.updateMuchie(oldPrietenie, newPrietenie);
    }

    @Override
    public Prietenie findOne(String[] strings) {
        Long id = parserPrietenie.parseId(strings[0]);
        return repoPrietenii.findOne(id);
    }

    @Override
    public Collection<Prietenie> findAll() {
        return repoPrietenii.findAll();
    }

    public Integer getNumarComunitati(){
        List<GrafListaAdiacenta<User, Prietenie>> comunitati = graf.componenteConexe();
        return comunitati.size();
    }

    public Pair<Set<User>, Integer> getCeaMaiSociabilaComunitate(){
        Pair<GrafListaAdiacenta<User, Prietenie>, Integer> comunitate = graf.componentWithLongestPath();
        return new Pair<>(comunitate.first.getNoduri(), comunitate.second);
    }
}