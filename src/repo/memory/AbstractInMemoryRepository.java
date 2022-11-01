package repo.memory;

import domain.Entity;
import domain.validation.Validator;
import exceptii.DuplicatedElementException;
import repo.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInMemoryRepository <ID, E extends Entity<ID>> implements Repository<ID, E> {
    protected Map<ID, E> entities;
    protected Validator<E> validator;

    /**
     * gaseste entitatea cu id-ul dat
     * @return entitatea cu id-ul dat
     * @param id - id-ul entitatii
     * @throws IllegalArgumentException - daca id-ul este null
     */
    @Override
    public E findOne(ID id) {
        if(id == null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    /**
     * @return toate entitatile
     */
    @Override
    public Collection<E> findAll() {
        return entities.values();
    }

    /**
     * salveaza entitatea
     * @param entity - entitatea de salvat
     * @return entitatea salvata sau null daca nu exista
     * @throws IllegalArgumentException - daca entitatea este null
     * @throws DuplicatedElementException - daca entitatea exista deja
     */
    @Override
    public E save(E entity) {
        if(entity == null)
            throw new IllegalArgumentException("entity must be not null");
        E old = entities.get(entity.getId());
        if(old != null)
            throw new DuplicatedElementException("entity already exists");
        validator.validate(entity);
        entities.put(entity.getId(), entity);
        return entity;
    }

    /**
     * sterge entitatea cu id-ul dat
     * @param id - id-ul entitatii de sters
     * @return entitatea stearsa sau null daca nu exista
     * @throws IllegalArgumentException - daca id-ul este null
     */
    @Override
    public E delete(ID id) {
        if(id == null)
            throw new IllegalArgumentException("id must be not null");
        return entities.remove(id);
    }

    /**
     * actualizeaza entitatea cu id-ul dat
     * @param id - id-ul entitatii de actualizat
     * @param entity - entitatea cu noile date
     * @return entitatea actualizata sau null daca nu exista
     * @throws IllegalArgumentException - daca id-ul sau entitatea sunt null
     */
    @Override
    public E update(ID id, E entity) {
        if(id == null)
            throw new IllegalArgumentException("id must be not null");
        if(entity == null)
            throw new IllegalArgumentException("entity must be not null");
        validator.validate(entity);
        return entities.put(id, entity);
    }
}
