package service;

import java.util.Collection;

public interface ServiceCRUD<E> {
    void add(String[] strings);
    void remove(String[] strings);
    void update(String[] strings);
    E findOne(String[] strings);
    Collection<E> findAll();
}
