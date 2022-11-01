package service;

import java.util.Collection;

public interface ServiceCRUD<E> {
    /**
     * Adauga un element in repository
     * @param strings - elementul de adaugat
     */
    void add(String[] strings);

    /**
     * Sterge un element din repository
     * @param strings - elementul de sters
     */
    void remove(String[] strings);

    /**
     * Actualizeaza un element
     * @param strings - elementul de actualizat
     */
    void update(String[] strings);

    /**
     * Verifica daca un element exista in container
     * @param strings - elementul de verificat
     * @return true - daca elementul exista in container, false - altfel
     */
    E findOne(String[] strings);

    /**
     * determina toate elementele din container
     * @return o colectie de elemente reprezentand elementele containerului
     */
    Collection<E> findAll();
}
