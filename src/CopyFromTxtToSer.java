import config.ApplicationContext;
import domain.Entity;
import domain.Prietenie;
import domain.User;
import domain.parser.Parser;
import domain.parser.PrietenieParser;
import domain.parser.UserParser;
import domain.validation.PrietenieValidator;
import domain.validation.UserValidator;
import repo.FileRepository;
import repo.InMemoryRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CopyFromTxtToSer {
    static <E extends Entity<Long>> void copyFromTxt(InMemoryRepository<Long, E> repo, String txtPath, Parser<E> parser) {
        Path path = Paths.get(txtPath);

        try(Stream<String> lines = Files.lines(path)){
            lines.forEach(line -> {
                String[] tokens = line.split(";");
                E entity = parser.parse(tokens);
                repo.save(entity);
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    static void copyUsers(){
        String pathUseriTxt = "./data/useri.txt";
        String pathUseriSer = ApplicationContext.getPROPERTIES().getProperty("file.useri");
        InMemoryRepository<Long, User> useriRepo = new InMemoryRepository<>(new UserValidator());
        Parser<User> userParser = new UserParser();
        copyFromTxt(useriRepo, pathUseriTxt, userParser);
        FileRepository<Long, User> useriFileRepo = new FileRepository<>(new UserValidator(), pathUseriSer);
        useriRepo.findAll().forEach(useriFileRepo::save);
    }

    static void copyPrietenii(){
        String pathPrieteniiTxt = "./data/prietenii.txt";
        String pathPrieteniiSer = ApplicationContext.getPROPERTIES().getProperty("file.prietenii");
        InMemoryRepository<Long, Prietenie> prieteniiRepo = new InMemoryRepository<>(new PrietenieValidator());
        Parser<Prietenie> prietenieParser = new PrietenieParser();
        copyFromTxt(prieteniiRepo, pathPrieteniiTxt, prietenieParser);
        FileRepository<Long, Prietenie> prieteniiFileRepo = new FileRepository<>(new PrietenieValidator(), pathPrieteniiSer);
        prieteniiRepo.findAll().forEach(prieteniiFileRepo::save);
    }

    public static void main(String[] args) {
        //copyUsers();
        //copyPrietenii();
    }
}
