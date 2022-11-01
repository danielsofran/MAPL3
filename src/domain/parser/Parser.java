package domain.parser;

import exceptii.ParsingException;

public abstract class Parser<T> {
    /**
     * verifica daca numarul de stringuri este cel corect
     * @param strings - stringurile de parsat
     * @param number - numarul de stringuri asteptat
     * @param nume - numele entitatii
     * @throws IllegalArgumentException
     */
    protected void checkNumber(String[] strings, int number, String nume) throws IllegalArgumentException {
        if(strings == null)
            throw new IllegalArgumentException("Parsare din null "+nume+" imposibila");
        if (strings.length != number)
            throw new IllegalArgumentException(nume+" permite parsarea a "+number+", nu "+strings.length + " stringuri");
    }

    /**
     * parseaza un vector string in entitatea corespunzatoare
     * @param strings - vectorul de stringuri
     * @return entitatea corespunzatoare
     * @throws ParsingException
     */
    public abstract T parse(String[] strings) throws ParsingException;

    /**
     * parseaza un string in id
     * @param string - stringul
     * @return Long - id-ul
     * @throws ParsingException
     */
    public Long parseId(String string) throws ParsingException {
        long id = -1L;
        try{
            id = Long.parseLong(string);
        }
        catch (Exception ex){
            throw new ParsingException("Id invalid!");
        }
        return id;
    }
}
