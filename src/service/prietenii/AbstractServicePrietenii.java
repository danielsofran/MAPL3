package service.prietenii;

import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import exceptii.DuplicatedElementException;
import exceptii.NotExistentException;
import graf.AlgoritmiGraf;
import graf.GrafListaAdiacenta;
import graf.StrategiiCelMaiLungDrum;
import repo.Repository;
import service.ServiceCRUD;
import utils.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractServicePrietenii implements ServiceCRUD<Prietenie> {
    protected Repository<Long, User> repoUser;
    protected Repository<Long, Prietenie> repoPrietenii;
    protected Parser<Prietenie> parserPrietenie;

    /**
     * determina id-urile unei prietenii: id-ul prieteniei, id-ul primului user si id-ul celui de-al doilea user
     * @param strings - string-urile de parsat
     * @return - prietenia cu id-urile determinate
     */
    private Prietenie fromIds(String[] strings){
        Long id = parserPrietenie.parseId(strings[0]);
        Long id1 = parserPrietenie.parseId(strings[1]);
        Long id2 = parserPrietenie.parseId(strings[2]);
        User user1 = repoUser.findOne(id1);
        User user2 = repoUser.findOne(id2);
        return new Prietenie(id, user1, user2);
    }

    /**
     * adauga o prietenie in repository si in graf
     * @param strings - elementul de adaugat
     */
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
    }

    /**
     * sterge o prietenie din repository si din graf
     * @param strings - prietenia de sters
     */
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
    }

    /**
     * modifica o prietenie din repository si din graf
     * @param strings - prietenia de modificat
     */
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
    }

    /**
     * determina o prietenie din repository
     * @param strings - string-ul id-ului prieteniei
     * @return - prietenia determinata
     * @throws NotExistentException - daca prietenia nu exista
     */
    @Override
    public Prietenie findOne(String[] strings) {
        Long id = parserPrietenie.parseId(strings[0]);
        Prietenie prietenie = repoPrietenii.findOne(id);
        if(prietenie == null)
            throw new NotExistentException("Prietenia nu exista!");
        return prietenie;
    }

    /**
     * determina toate prietenile din repository
     * @return - lista de prietenii
     */
    @Override
    public Collection<Prietenie> findAll() {
        return repoPrietenii.findAll();
    }

    /**
     * determina graful de prietenii
     * @return - graful de prietenii, representat prin lista de adiacenta
     */
    private GrafListaAdiacenta<User, Prietenie> getGraf() {
        GrafListaAdiacenta<User, Prietenie> graf = new GrafListaAdiacenta<User, Prietenie>();
        repoUser.findAll().forEach(graf::addNod);
        List<Prietenie> prietenii = new ArrayList<>(repoPrietenii.findAll());
        prietenii.forEach(graf::addMuchie);
        return graf;
    }
    /**
     * determina numarul de comunitati
     * @return - numarul de comunitati
     */
    public Integer getNumarComunitati(){
        List<GrafListaAdiacenta<User, Prietenie>> comunitati = getGraf().componenteConexe();
        return comunitati.size();
    }

    /**
     * determina cea mai sociabila comunitate
     * @param strategie - algoritmul pe grafuri folosit pentru determinarea componentei cu cel mai lung drum
     * @return - pereche formata din multimea de useri din comunitate si scorul comunitatii (cel mai lung drum din graf)
     */
    public Pair<Set<User>, Integer> getCeaMaiSociabilaComunitate(StrategiiCelMaiLungDrum strategie){
        Pair<GrafListaAdiacenta<User, Prietenie>, Integer> comunitate;
        GrafListaAdiacenta<User, Prietenie> graf = getGraf();
        if(strategie == StrategiiCelMaiLungDrum.Backtracking) comunitate = AlgoritmiGraf.componentWithLongestPath(graf);
        else comunitate = AlgoritmiGraf.componentWithLongestPath2(graf);
        return new Pair<>(comunitate.first.getNoduri(), comunitate.second);
    }
}
