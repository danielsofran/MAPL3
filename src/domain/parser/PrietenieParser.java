package domain.parser;

import domain.Prietenie;

public class PrietenieParser extends Parser<Prietenie>{
    /**
     * parseaza un string in entitatea de tip Prietenie
     * @param strings - vectorul de stringuri
     * @return entitatea de tip Prietenie
     */
    @Override
    public Prietenie parse(String[] strings) {
        if(strings.length != 3)
            throw new IllegalArgumentException("Numar invalid de argumente!");
        Prietenie prietenie = new Prietenie();
        prietenie.setId(parseId(strings[0]));
        prietenie.setFirst(parseId(strings[1]));
        prietenie.setSecond(parseId(strings[2]));
        return prietenie;
    }
}
