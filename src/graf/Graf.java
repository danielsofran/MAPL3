package graf;

import domain.Pereche;
import exceptii.DuplicatedElementException;
import exceptii.NotExistentException;

import java.util.List;

public interface Graf<Nod, Muchie extends Pereche<Nod>> {
    void addNod(Nod nod) throws DuplicatedElementException;
    void addMuchie(Muchie muchie) throws DuplicatedElementException;
    void removeNod(Nod nod) throws NotExistentException;
    void removeMuchie(Muchie muchie) throws NotExistentException;
    void updateNod(Nod nod, Nod newNod) throws NotExistentException;
    void updateMuchie(Muchie muchie, Muchie newMuchie) throws NotExistentException;
    List<Nod> getNoduri();
    List<Muchie> getMuchii();
    List<Nod> getVecini(Nod nod);
    List<Muchie> getMuchiiOut(Nod nod);
    int getGrad(Nod nod) throws NotExistentException;
    int getNrNoduri();
    int getNrMuchii();
}