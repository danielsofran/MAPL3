package utils;

public interface Pereche<T> {
    /**
     * metoda care returneaza primul element din pereche
     * @return primul element din pereche
     */
    T getFirst();

    /**
     * metoda care returneaza al doilea element din pereche
     * @return al doilea element din pereche
     */
    T getSecond();

    /**
     * metoda care seteaza primul element din pereche
     * @param first - primul element din pereche
     */
    void setFirst(T first);

    /**
     * metoda care seteaza al doilea element din pereche
     * @param second - al doilea element din pereche
     */
    void setSecond(T second);
}
