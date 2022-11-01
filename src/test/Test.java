package test;

import domain.Prietenie;
import exceptii.DuplicatedElementException;
import exceptii.NotExistentException;
import exceptii.ValidationException;
import service.Service;

import java.util.LinkedList;
import java.util.List;

public class Test {
    /**
     * test
     * @param args - args
     */
    public static void main(String[] args) {
        Service service = new Service();
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
        
        assert service.getServicePrietenii().getCeaMaiSociabilaComunitate().second == 0;
    }
}
