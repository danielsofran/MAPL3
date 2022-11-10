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
import java.util.stream.Collectors;

public abstract class AbstractServicePrietenii implements ServiceCRUD<Prietenie> {
    protected Repository<Long, User> repoUser;
    protected Repository<Long, Prietenie> repoPrietenii;
    protected Parser<Prietenie> parserPrietenie;
    protected Long idGenerator;

    /**
     * adauga o prietenie in repository si in graf
     * @param strings - elementul de adaugat
     */
    @Override
    public void add(String[] strings) {
        String sid = idGenerator.toString();
        String[] newstrings = new String[strings.length + 1];
        newstrings[0] = sid;
        System.arraycopy(strings, 0, newstrings, 1, strings.length);
        Prietenie prietenie = parserPrietenie.parse(newstrings);
        // find the users
        List<Prietenie> prietenii = new ArrayList<>(repoPrietenii.findAll());
        prietenii.forEach(prietenie1 -> {
            if (prietenie1.equals(prietenie))
                throw new DuplicatedElementException("Prietenia exista deja!");
        });
        repoPrietenii.save(prietenie);
        idGenerator++;
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
        Prietenie newPrietenie = parserPrietenie.parse(strings);
        repoPrietenii.update(id, newPrietenie);
    }

    /**
     * determina o prietenie din repository
     * @param strings - string-ul id-ului prieteniei, sau al celor 2 useri
     * @return - prietenia determinata
     * @throws NotExistentException - daca prietenia nu exista
     */
    @Override
    public Prietenie findOne(String[] strings) {
        switch (strings.length) {
            case 1:
                Long id = parserPrietenie.parseId(strings[0]);
                Prietenie pr = repoPrietenii.findOne(id);
                if (pr == null)
                    throw new NotExistentException("Prietenia nu exista!");
                return pr;
            case 2:
                Long id1 = parserPrietenie.parseId(strings[0]);
                Long id2 = parserPrietenie.parseId(strings[1]);
                Prietenie prietenie0 = new Prietenie(-1L, id1, id2);
                repoPrietenii.findAll().forEach(prietenie -> {
                    if (prietenie.equals(prietenie0))
                        prietenie0.setId(prietenie.getId());
                });
                if (prietenie0.getId() == -1L)
                    throw new NotExistentException("Prietenia nu exista!");
                return prietenie0;
            default:
                throw new IllegalArgumentException("Numar de argumente invalid!");
        }
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
    private GrafListaAdiacenta<Long, Prietenie> getGraf() {
        GrafListaAdiacenta<Long, Prietenie> graf = new GrafListaAdiacenta<>();
        repoUser.findAll().stream().map(User::getId).forEach(graf::addNod);
        List<Prietenie> prietenii = new ArrayList<>(repoPrietenii.findAll());
        prietenii.forEach(graf::addMuchie);
        return graf;
    }
    /**
     * determina numarul de comunitati
     * @return - numarul de comunitati
     */
    public Integer getNumarComunitati(){
        List<GrafListaAdiacenta<Long, Prietenie>> comunitati = getGraf().componenteConexe();
        return comunitati.size();
    }

    /**
     * determina cea mai sociabila comunitate
     * @param strategie - algoritmul pe grafuri folosit pentru determinarea componentei cu cel mai lung drum
     * @return - pereche formata din multimea de useri din comunitate si scorul comunitatii (cel mai lung drum din graf)
     */
    public Pair<Set<User>, Integer> getCeaMaiSociabilaComunitate(StrategiiCelMaiLungDrum strategie){
        Pair<GrafListaAdiacenta<Long, Prietenie>, Integer> comunitate;
        GrafListaAdiacenta<Long, Prietenie> graf = getGraf();
        if(strategie == StrategiiCelMaiLungDrum.Backtracking) comunitate = AlgoritmiGraf.componentWithLongestPath(graf);
        else comunitate = AlgoritmiGraf.componentWithLongestPath2(graf);
        Set<User> users = comunitate.getFirst().getNoduri().stream().map(repoUser::findOne).collect(Collectors.toSet());
        return new Pair<>(users, comunitate.getSecond());
    }
}
