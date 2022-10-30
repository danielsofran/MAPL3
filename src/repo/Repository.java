package repo;

import domain.Entity;

import java.util.Collection;

public interface Repository <ID, E extends Entity<ID>> {
    E findOne(ID id);
    Collection<E> findAll();
    E save(E entity);
    E delete(ID id);
    E update(ID id, E entity);
}
