package domain.parser;

import domain.Prietenie;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PrietenieParser extends Parser<Prietenie>{
    private static final int NumberOfFields = 4;

    /**
     * parseaza un string in entitatea de tip Prietenie
     * @param strings - vectorul de stringuri
     * @return entitatea de tip Prietenie
     */
    @Override
    public Prietenie parse(String[] strings) {
        String[] user1 = Arrays.copyOfRange(strings, 1, 1+NumberOfFields);
        String[] user2 = Arrays.copyOfRange(strings, 1+NumberOfFields, 1+2*NumberOfFields);
        Prietenie prietenie = new Prietenie();
        prietenie.setId(parseId(strings[0]));
        prietenie.setFirst(new UserParser().parse(user1));
        prietenie.setSecond(new UserParser().parse(user2));
        return prietenie;
    }
}
