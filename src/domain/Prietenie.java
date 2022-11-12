package domain;

import utils.Pereche;

import java.util.Objects;

public class Prietenie extends Entity<Long> implements Pereche<Long, Long> {
    private Long id_user1;
    private Long id_user2;

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
        id_user1 = user1.getId();
        id_user2 = user2.getId();
    }

    /**
     * Construcor cu parametri
     * @param id - id-ul prieteniei
     * @param user1 - primul user
     * @param user2 - al doilea user
     */
    public Prietenie(Long id, User user1, User user2){
        super(id);
        id_user1 = user1.getId();
        id_user2 = user2.getId();
    }

    /**
     * Constructor cu parametri
     * @param id - id-ul prieteniei
     * @param id1 - id-ul primului user
     * @param id2 - id-ul celui de-al doilea user
     */
    public Prietenie(Long id, Long id1, Long id2) {
        super(id);
        id_user1 = id1;
        id_user2 = id2;
    }

    /**
     * getter pentru primul user
     * @return id-ul primului user
     */
    @Override
    public Long getFirst() {
        return id_user1;
    }

    /**
     * getter pentru al doilea user
     * @return id-ul celui de-al doilea user
     */
    @Override
    public Long getSecond() {
        return id_user2;
    }

    /**
     * setter pentru primul user
     * @param user1 - id-ul primului user
     */
    @Override
    public void setFirst(Long user1) {
        this.id_user1 = user1;
    }

    /**
     * setter pentru al doilea user
     * @param user2 - id-ul celui de-al doilea user
     */
    @Override
    public void setSecond(Long user2) {
        this.id_user2 = user2;
    }

    /**
     * verifica daca unul dintre userii prieteniei are id-ul dat
     * @param id - id-ul dat
     * @return true - daca unul dintre userii prieteniei are id-ul dat
     */
    public boolean contains(Long id){
        return id_user1.equals(id) || id_user2.equals(id);
    }

    /**
     * verifica daca prietenia are cei doi useri cu id-urile id1 si id2
     * @apiNote nu conteaza ordinea id-urilor
     * @param id1 - id-ul primului user
     * @param id2 - id-ul celui de-al doilea user
     * @return true - daca prietenia are cei doi useri cu id-urile id1 si id2
     */
    public boolean contains(Long id1, Long id2){
        return (id_user1.equals(id1) && id_user2.equals(id2)) || (id_user1.equals(id2) && id_user2.equals(id1));
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
                id_user1.equals(prietenie.id_user1) && id_user2.equals(prietenie.id_user2) ||
                id_user1.equals(prietenie.id_user2) && id_user2.equals(prietenie.id_user1);
    }

    /**
     * calculeaza hashcode-ul prieteniei
     * @return hashcode-ul prieteniei
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), id_user1, id_user2);
    }

    /**
     * transforma prietenia intr-un string
     * @return string-ul prieteniei
     */
    @Override
    public String toString() {
        return "User-ul cu id-ul " + id_user1 + " este prieten cu userul cu id-ul " + id_user2;
    }
}
