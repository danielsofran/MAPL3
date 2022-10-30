package domain.parser;

import domain.Entity;

public class EntityParser extends Parser<Long>{
    @Override
    public Long parse(String[] strings) {
        checkNumber(strings, 1, "Entity");
        Long id;
        try{
            id = Long.parseLong(strings[0]);
        }
        catch (Exception ex){
            throw new
        }
    }
}
