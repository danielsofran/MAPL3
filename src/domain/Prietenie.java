package domain;

import java.math.BigInteger;
import java.util.Objects;

public class Prietenie extends Entity<Long> implements Pereche<User> {
    private User user1 = null;
    private User user2 = null;

    public Prietenie(){}

    public Prietenie(User user1, User user2){
        super();
        this.user1 = user1;
        this.user2 = user2;
    }

    @Override
    public User getFirst() {
        return user1;
    }

    @Override
    public User getSecond() {
        return user2;
    }

    @Override
    public void setFirst(User user1) {
        this.user1 = user1;
    }

    @Override
    public void setSecond(User user2) {
        this.user2 = user2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prietenie prietenie = (Prietenie) o;
        return getId().equals(prietenie.getId()) ||
                user1.equals(prietenie.user1) && user2.equals(prietenie.user2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1, user2);
    }

    @Override
    public String toString() {
        return "Prietenie{" +
                "user1=" + user1 +
                ", user2=" + user2 +
                '}';
    }

    public static Prietenie fromStrings(String... strings) throws IllegalArgumentException
    {
        return null;
    }
}
