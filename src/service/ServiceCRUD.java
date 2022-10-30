package service;

import domain.User;

public interface ServiceCRUD<E> {
    void add(String[] strings);
    void remove(String[] strings);
    void update(String[] strings);
    Iterable<E> findAllUsers();
}
