package domain;

public interface Pereche<T> {
    T getFirst();
    T getSecond();
    void setFirst(T first);
    void setSecond(T second);
}
