package domain.parser;

public abstract class Parser<T> {
    protected void checkNumber(String[] strings, int number, String nume) throws IllegalArgumentException {
        if(strings == null)
            throw new IllegalArgumentException("Parsare din null "+nume+" imposibila");
        if (strings.length != 1)
            throw new IllegalArgumentException(nume+" permite parsarea a "+number+", nu "+strings.length + " stringuri");
    }

    public abstract T parse(String[] strings);
}
