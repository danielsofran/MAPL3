package repo;

import domain.Entity;
import exceptii.DuplicatedElementException;

import java.util.Collection;

public interface Repository <ID, E extends Entity<ID>> {
    /**
     * gaseste entitatea cu id-ul dat
     * @param id - id-ul entitatii
     * @return entitatea gasita sau null daca nu exista
     * @throws IllegalArgumentException - daca id-ul este null
     */
    E findOne(ID id) throws IllegalArgumentException;

    /**
     * @return toate entitatile
     */
    Collection<E> findAll();
    /**
     * salveaza entitatea
     * @param entity - entitatea de salvat
     * @return entitatea salvata sau null daca nu exista
     * @throws IllegalArgumentException - daca entitatea este null
     * @throws DuplicatedElementException - daca entitatea exista deja
     */
    E save(E entity) throws IllegalArgumentException, DuplicatedElementException;
    /**
     * sterge entitatea cu id-ul dat
     * @param id - id-ul entitatii de sters
     * @return entitatea stearsa sau null daca nu exista
     * @throws IllegalArgumentException - daca id-ul este null
     */
    E delete(ID id) throws IllegalArgumentException;

    /**
     * actualizeaza entitatea cu id-ul dat
     * @param id - id-ul entitatii de actualizat
     * @param entity - entitatea cu noile date
     * @return entitatea actualizata sau null daca nu exista
     * @throws IllegalArgumentException - daca id-ul sau entitatea sunt null
     */
    E update(ID id, E entity) throws IllegalArgumentException;
}
