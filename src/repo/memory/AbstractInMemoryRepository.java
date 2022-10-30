package repo.memory;

import domain.Entity;
import domain.validation.Validator;
import repo.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInMemoryRepository <ID, E extends Entity<ID>> implements Repository<ID, E> {
    protected Map<ID, E> entities;
    protected Validator<E> validator;

    @Override
    public E findOne(ID id) {
        if(id == null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    @Override
    public Collection<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) {
        if(entity == null)
            throw new IllegalArgumentException("entity must be not null");
        E old = entities.get(entity.getId());
        if(old != null)
            return old;
        validator.validate(entity);
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public E delete(ID id) {
        if(id == null)
            throw new IllegalArgumentException("id must be not null");
        return entities.remove(id);
    }

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
