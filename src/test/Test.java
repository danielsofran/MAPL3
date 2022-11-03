package test;

import domain.Entity;
import domain.Prietenie;
import exceptii.DuplicatedElementException;
import exceptii.NotExistentException;
import exceptii.ValidationException;
import graf.StrategiiCelMaiLungDrum;
import service.Service;

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
        Service service = new Service();
        // delete all users and prietenii
        List<Long> idUsers = service.getServiceUser().findAll().stream().map(Entity::getId).collect(Collectors.toCollection(LinkedList::new));
        List<Long> idPrietenii = service.getServicePrietenii().findAll().stream().map(Entity::getId).collect(Collectors.toCollection(LinkedList::new));
        Consumer<Long> delete = id -> {
            try {
                String[] idString = {id.toString()};
                service.getServiceUser().remove(idString);
            } catch (NotExistentException e) {
                e.printStackTrace();
            }
        };
        idUsers.forEach(delete);
        idPrietenii.forEach(delete);
        String[] user1 = {"1", "Numehthg", "Email1", "Pasw1"};
        String[] user2 = {"2", "Nume ejfbeh jkdfb", "Email2", "Pasw2"};
        String[] user3 = {"3", "Nume ejfbeh jkdfb", "Email2", "Pasw2"};
        String[] user4 = {"3", "Nume ejfbeh jkdfb", "Email2", "Pasw2"};
        String[] user5 = {"diedbu", "Nume ejfbeh jkdfb", "Email2", "Pasw2"};
        String[] pr1 = {"1", "1", "2"};
        String[] pr2 = {"2", "1", "3"};
        String[] pr3 = {"3", "2", "3"};
        String[] pr4 = {"4", "2", "3"};
        String[] pr5 = {"3", "1", "4"};

        service.getServiceUser().add(user1);
        service.getServiceUser().add(user2);
        service.getServiceUser().add(user3);
        try{service.getServiceUser().add(user4);}
        catch (DuplicatedElementException ignored){}
        try{service.getServiceUser().add(user5);}
        catch(ValidationException ignored){}

        service.getServicePrietenii().add(pr1);
        service.getServicePrietenii().add(pr2);
        service.getServicePrietenii().add(pr3);
        try{service.getServicePrietenii().add(pr4);}
        catch (DuplicatedElementException ignored){}
        try{service.getServicePrietenii().add(pr5);}
        catch (DuplicatedElementException ignored){}

        service.getServicePrietenii().remove(pr3);
        try{service.getServicePrietenii().remove(pr4);}
        catch (NotExistentException ignored){}

        service.getServiceUser().remove(user1);
        List<Prietenie>prietenii = new LinkedList<>(service.getServicePrietenii().findAll());
        assert prietenii.size() == 0;
        
        assert service.getServicePrietenii().getCeaMaiSociabilaComunitate(StrategiiCelMaiLungDrum.N_DFSuri).second == 0;

        service.getServiceUser().remove(user2);
        service.getServiceUser().remove(user3);

        assert service.getServiceUser().findAll().isEmpty();
        assert service.getServicePrietenii().findAll().isEmpty();

        service.getServiceUser().add(user1);
        service.getServiceUser().add(user2);
        service.getServiceUser().add(user3);

        service.getServicePrietenii().add(pr1);
        service.getServicePrietenii().add(pr2);
        service.getServicePrietenii().add(pr3);

        assert service.getServiceUser().findAll().size() == 3;
        assert service.getServicePrietenii().findAll().size() == 3;

        service.getServiceUser().remove(user1);
        assert service.getServicePrietenii().findAll().size() == 1;
    }
}
