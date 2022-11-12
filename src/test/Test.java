package test;

import controller.Controller;
import domain.Entity;
import domain.Prietenie;
import domain.UserDetails;
import exceptii.*;
import graf.StrategiiCelMaiLungDrum;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Test {
    /**
     * test
     * @param args - args
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.clear();
        // delete all users and prietenii
        UserDetails user1 = new UserDetails(); user1.add("Numehthg", "Email1", "Pasw1");
        UserDetails user2 = new UserDetails(); user2.add("Nume ejfbeh jkdfb", "Email2", "Pasw2");
        UserDetails user3 = new UserDetails(); user3.add("Nume ejfbeh jkdfb", "Email2", "Pasw2");

        controller.getServiceUser().add(user1);
        controller.getServiceUser().add(user2);
        controller.getServiceUser().add(user3);

        controller.getServicePrietenii().add(1L, 2L);
        controller.getServicePrietenii().add(1L, 3L);
        controller.getServicePrietenii().add(2L, 3L);
        try{controller.getServicePrietenii().add(2L, 3L); assert false;}
        catch (DuplicatedElementException ignored){}
        try{controller.getServicePrietenii().add(1L, 4L); assert false;}
        catch (RepositoryException ignored){}

        controller.getServicePrietenii().remove(3L);
        try{controller.getServicePrietenii().remove(1L, 4L); assert false;}
        catch (NotExistentException ignored){}

        controller.getServiceUser().remove(1L);
        assert controller.getServicePrietenii().findAll().isEmpty();

        assert controller.getServicePrietenii().getCeaMaiSociabilaComunitate(StrategiiCelMaiLungDrum.Backtracking).getSecond() == 0;
        assert controller.getServicePrietenii().getCeaMaiSociabilaComunitate(StrategiiCelMaiLungDrum.N_DFSuri).getSecond() == 0;

        controller.getServiceUser().remove(2L);
        controller.getServiceUser().remove(3L);

        assert controller.getServiceUser().findAll().isEmpty();
        assert controller.getServicePrietenii().findAll().isEmpty();

        controller.getServiceUser().add(user1);
        controller.getServiceUser().add(user2);
        controller.getServiceUser().add(user3);

        try{ controller.getServicePrietenii().add(1L, 2L); assert false;}
        catch (NotExistentException ignored){}
        controller.getServicePrietenii().add(4L, 5L);
        controller.getServicePrietenii().add(5L, 6L);
        controller.getServicePrietenii().add(4L, 6L);

        assert controller.getServiceUser().findAll().size() == 3;
        assert controller.getServicePrietenii().findAll().size() == 3;

        controller.getServiceUser().remove(4L);
        assert controller.getServicePrietenii().findAll().size() == 1;
    }
}
