package domain;

import utils.Pereche;

import java.util.Objects;

public class Prietenie extends Entity<Long> implements Pereche<User> {
    private User user1 = null;
    private User user2 = null;

    /**
     * Constructorul implicit
     */
    public Prietenie(){}

    /**
     * Constructorul cu parametri
     * @param user1 - primul user
     * @param user2 - al doilea user
     */
    public Prietenie(User user1, User user2){
        super();
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * Construcor cu parametri
     * @param id - id-ul prieteniei
     * @param user1 - primul user
     * @param user2 - al doilea user
     */
    public Prietenie(Long id, User user1, User user2){
        super(id);
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * getter pentru primul user
     * @return primul user
     */
    @Override
    public User getFirst() {
        return user1;
    }

    /**
     * getter pentru al doilea user
     * @return al doilea user
     */
    @Override
    public User getSecond() {
        return user2;
    }

    /**
     * setter pentru primul user
     * @param user1 - primul user
     */
    @Override
    public void setFirst(User user1) {
        this.user1 = user1;
    }

    /**
     * setter pentru al doilea user
     * @param user2 - al doilea user
     */
    @Override
    public void setSecond(User user2) {
        this.user2 = user2;
    }

    /**
     * verifica daca doua prietenii sunt identice
     * @param o - prietenia cu care se compara
     * @return true daca sunt egale, false altfel
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prietenie prietenie = (Prietenie) o;
        return getId().equals(prietenie.getId()) ||
                user1.equals(prietenie.user1) && user2.equals(prietenie.user2) ||
                user1.equals(prietenie.user2) && user2.equals(prietenie.user1);
    }

    /**
     * calculeaza hashcode-ul prieteniei
     * @return hashcode-ul prieteniei
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), user1, user2);
    }

    /**
     * transforma prietenia intr-un string
     * @return string-ul prieteniei
     */
    @Override
    public String toString() {
        return user1 + " este prieten cu " + user2;
    }
}
