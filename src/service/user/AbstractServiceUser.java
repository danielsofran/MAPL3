package service.user;

import domain.User;
import domain.parser.Parser;
import domain.parser.UserParser;
import repo.Repository;
import service.ServiceCRUD;

public class AbstractServiceUser implements ServiceCRUD<User> {
    private Repository<Long, User> repository;
    private Parser<User> parser;

    @Override
    public void add(String[] strings) {
        User user = parser.parse(strings);
        repository.save()
    }

    @Override
    public void remove(String[] strings) {

    }

    @Override
    public void update(String[] strings) {

    }

    @Override
    public Iterable<User> findAllUsers() {
        return null;
    }
}
