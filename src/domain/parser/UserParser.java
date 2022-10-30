package domain.parser;

import domain.User;

import java.util.Arrays;

public class UserParser extends Parser<User> {
    @Override
    public User parse(String[] strings) {
        checkNumber(strings, 4, "User");
        User user = new User();
        EntityParser entityParser = new EntityParser();
        String[] id = {strings[0]};
        user.setId(entityParser.parse(id));
        user.setName(strings[1]);
        user.setPassword(strings[2]);
        user.setEmail(strings[3]);
        return user;
    }
}
