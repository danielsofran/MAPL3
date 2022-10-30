package graf;

import domain.Pereche;
import exceptii.DuplicatedElementException;
import exceptii.NotExistentException;

import java.util.*;

public class GrafListeMuchii<Nod, Muchie extends Pereche<Nod>> implements Graf<Nod, Muchie> {
    private Set<Nod> noduri;
    private List<Muchie> muchii;

    public GrafListeMuchii() {
        this.noduri = new HashSet<>();
        this.muchii = new LinkedList<>();
    }

    public GrafListeMuchii(List<Muchie> muchii) {
        this.noduri = new HashSet<>();
        this.muchii = muchii;
        for(Muchie muchie : muchii) {
            noduri.add(muchie.getFirst());
            noduri.add(muchie.getSecond());
        }
    }

    @Override
    public void addNod(Nod nod) throws DuplicatedElementException {
        if (nod == null)
            throw new IllegalArgumentException("Nod must be not null");
        if (noduri.contains(nod))
            throw new DuplicatedElementException("Nodul exista deja in graf!");
        noduri.add(nod);
    }

    @Override
    public void addMuchie(Muchie muchie) throws DuplicatedElementException {
        if (muchie == null)
            throw new IllegalArgumentException("Muchie must be not null");
        if (muchii.contains(muchie))
            throw new DuplicatedElementException("Muchia exista deja in graf!");
        noduri.add(muchie.getFirst());
        noduri.add(muchie.getSecond());
        muchii.add(muchie);
    }

    @Override
    public void removeNod(Nod nod) throws NotExistentException {
        if (nod == null)
            throw new IllegalArgumentException("Nod must be not null");
        if (!noduri.contains(nod))
            throw new NotExistentException("Nodul nu exista in graf!");
        muchii.removeIf(muchie -> muchie.getFirst().equals(nod) || muchie.getSecond().equals(nod));
        noduri.remove(nod);
    }

    @Override
    public void removeMuchie(Muchie muchie) throws NotExistentException {
        if (muchie == null)
            throw new IllegalArgumentException("Muchie must be not null");
        if (!muchii.contains(muchie))
            throw new NotExistentException("Muchia nu exista in graf!");
        muchii.remove(muchie);
    }

    @Override
    public void updateNod(Nod nod, Nod newNod) throws NotExistentException {
        if (nod == null || newNod == null)
            throw new IllegalArgumentException("Nod must be not null");
        if (!noduri.contains(nod))
            throw new NotExistentException("Nodul nu exista in graf!");
        noduri.remove(nod);
        noduri.add(newNod);
        muchii.forEach(muchie -> {
            if (muchie.getFirst().equals(nod))
                muchie.setFirst(newNod);
            if (muchie.getSecond().equals(nod))
                muchie.setSecond(newNod);
        });
    }

    @Override
    public void updateMuchie(Muchie muchie, Muchie newMuchie) throws NotExistentException {
        if (muchie == null || newMuchie == null)
            throw new IllegalArgumentException("Muchie must be not null");
        if (!muchii.contains(muchie))
            throw new NotExistentException("Muchia nu exista in graf!");
        muchii.remove(muchie);
        muchii.add(newMuchie);
        noduri.add(newMuchie.getFirst());
        noduri.add(newMuchie.getSecond());
    }

    @Override
    public List<Nod> getNoduri() {
        return new LinkedList<>(noduri);
    }

    @Override
    public List<Muchie> getMuchii() {
        return muchii;
    }

    @Override
    public List<Nod> getVecini(Nod nod) {
        List<Nod> vecini = new LinkedList<>();
        for(Muchie muchie : muchii)
            if (muchie.getFirst().equals(nod))
                vecini.add(muchie.getSecond());
            else if(muchie.getSecond().equals(nod))
                vecini.add(muchie.getFirst());
        return vecini;
    }

    @Override
    public List<Muchie> getMuchiiOut(Nod nod) {
        List<Muchie> muchiiOut = new LinkedList<>();
        for(Muchie muchie : muchii)
            if (muchie.getFirst().equals(nod))
                muchiiOut.add(muchie);
        return muchiiOut;
    }

    @Override
    public int getGrad(Nod nod) {
        return getVecini(nod).size();
    }

    @Override
    public int getNrNoduri() {
        return noduri.size();
    }

    @Override
    public int getNrMuchii() {
        return muchii.size();
    }

}
