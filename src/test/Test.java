package test;

import controller.Controller;
import domain.Entity;
import domain.Prietenie;
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
//        Controller controller = new Controller();
//        // delete all users and prietenii
//        String[] user1 = {"Numehthg", "Email1", "Pasw1"};
//        String[] user2 = {"Nume ejfbeh jkdfb", "Email2", "Pasw2"};
//        String[] user3 = {"Nume ejfbeh jkdfb", "Email2", "Pasw2"};
//        String[] pr1 = {"1", "2"};
//        String[] pr2 = {"1", "3"};
//        String[] pr3 = {"2", "3"};
//        String[] pr4 = {"2", "3"};
//        String[] pr5 = {"1", "4"};
//
//        controller.getServiceUser().add(user1);
//        controller.getServiceUser().add(user2);
//        controller.getServiceUser().add(user3);
//
//        controller.getServicePrietenii().add(pr1);
//        controller.getServicePrietenii().add(pr2);
//        controller.getServicePrietenii().add(pr3);
//        try{controller.getServicePrietenii().add(pr4);}
//        catch (DuplicatedElementException ignored){}
//        try{controller.getServicePrietenii().add(pr5);}
//        catch (RepositoryException ignored){}
//
//        controller.getServicePrietenii().remove(pr3);
//        try{controller.getServicePrietenii().remove(pr4);}
//        catch (NotExistentException ignored){}
//
//        controller.getServiceUser().remove(new String[]{"1"});
//        List<Prietenie>prietenii = new LinkedList<>(controller.getServicePrietenii().findAll());
//        assert prietenii.size() == 0;
//
//        assert controller.getServicePrietenii().getCeaMaiSociabilaComunitate(StrategiiCelMaiLungDrum.Backtracking).getSecond() == 0;
//
//        controller.getServiceUser().remove(new String[]{"2"});
//        controller.getServiceUser().remove(new String[]{"3"});
//
//        assert controller.getServiceUser().findAll().isEmpty();
//        assert controller.getServicePrietenii().findAll().isEmpty();
//
//        controller.getServiceUser().add(user1);
//        controller.getServiceUser().add(user2);
//        controller.getServiceUser().add(user3);
//
//        try{ controller.getServicePrietenii().add(pr1);}
//        catch (NotExistentException ignored){}
//        controller.getServicePrietenii().add(new String[]{"4", "5"});
//        controller.getServicePrietenii().add(new String[]{"5", "6"});
//        controller.getServicePrietenii().add(new String[]{"4", "6"});
//
//        assert controller.getServiceUser().findAll().size() == 3;
//        assert controller.getServicePrietenii().findAll().size() == 3;
//
//        controller.getServiceUser().remove(new String[]{"4"});
//        assert controller.getServicePrietenii().findAll().size() == 1;
    }
}
