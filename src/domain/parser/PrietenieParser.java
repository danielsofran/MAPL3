package domain.parser;

import domain.Prietenie;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PrietenieParser extends Parser<Prietenie>{
    private static final int NumberOfFields = 4;
    @Override
    public Prietenie parse(String[] strings) {
        String[] id = Arrays.copyOfRange(strings, 0, 1);
        String[] user1 = Arrays.copyOfRange(strings, 1, 1+NumberOfFields);
        String[] user2 = Arrays.copyOfRange(strings, 1+NumberOfFields, 1+2*NumberOfFields);
        Prietenie prietenie = new Prietenie();
        prietenie.setId(new EntityParser().parse(id));
        prietenie.setFirst(new UserParser().parse(user1));
        prietenie.setSecond(new UserParser().parse(user2));
        return prietenie;
    }
}
