package repo.memory;

import domain.Entity;
import domain.parser.Parser;
import domain.validation.Validator;
import repo.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InMemoryRepository<ID, E extends Entity<ID>> extends AbstractInMemoryRepository<ID, E> {
    /**
     * Constructor, initializeaza validatorul si containerul
     * @param validator - validatorul de entitati
     */
    public InMemoryRepository(Validator<E> validator){
        this.validator = validator;
        this.entities = new HashMap<>();
    }
}
