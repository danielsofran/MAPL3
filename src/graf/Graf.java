package graf;

import utils.Pereche;
import exceptii.DuplicatedElementException;
import exceptii.NotExistentException;

import java.util.Collection;

public interface Graf<Nod, Muchie extends Pereche<Nod>> {
    /**
     * Adauga un nod in graf
     * @param nod - nodul de adaugat
     * @throws DuplicatedElementException - daca nodul exista deja in graf
     */
    void addNod(Nod nod) throws DuplicatedElementException;
    /**
     * Adauga o muchie in graf
     * @param muchie - muchia de adaugat
     * @throws DuplicatedElementException - daca muchia exista deja in graf
     */
    void addMuchie(Muchie muchie) throws DuplicatedElementException;
    /**
     * Sterge un nod din graf
     * @param nod - nodul de sters
     * @throws NotExistentException - daca nodul nu exista in graf
     */
    void removeNod(Nod nod) throws NotExistentException;
    /**
     * Sterge o muchie din graf
     * @param muchie - muchia de sters
     * @throws NotExistentException - daca muchia nu exista in graf
     */
    void removeMuchie(Muchie muchie) throws NotExistentException;
    /**
     * Verifica daca un nod exista in graf
     * @param nod - nodul de verificat
     * @return true - daca nodul exista in graf, false - altfel
     */
    void updateNod(Nod nod, Nod newNod) throws NotExistentException;
    /**
     * Verifica daca o muchie exista in graf
     * @param muchie - muchia de verificat
     * @return true - daca muchia exista in graf, false - altfel
     */
    void updateMuchie(Muchie muchie, Muchie newMuchie) throws NotExistentException;

    /**
     * determina toate nodurile din graf
     * @return o colectie de noduri reprezentand nodurile grafului
     */
    Collection<Nod> getNoduri();
}