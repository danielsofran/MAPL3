package repo.memory;

import domain.Entity;
import domain.validation.Validator;
import repo.Repository;

import java.util.HashMap;

public class InMemoryRepository<ID, E extends Entity<ID>> extends AbstractInMemoryRepository<ID, E> {
    public InMemoryRepository(Validator<E> validator){
        this.validator = validator;
        this.entities = new HashMap<>();
    }
}
