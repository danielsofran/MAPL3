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

    public static void main(String[] args) {
        String pathUseriTxt = "./data/useri.txt";
        String pathPrieteniiTxt = "./data/prietenii.txt";
        String pathUseriSer = ApplicationContext.getPROPERTIES().getProperty("file.useri");
        String pathPrieteniiSer = ApplicationContext.getPROPERTIES().getProperty("file.prietenii");
        InMemoryRepository<Long, User> useriRepo = new InMemoryRepository<>(new UserValidator());
        InMemoryRepository<Long, Prietenie> prieteniiRepo = new InMemoryRepository<>(new PrietenieValidator());
        Parser<User> userParser = new UserParser();
        Parser<Prietenie> prietenieParser = new PrietenieParser();
        copyFromTxt(useriRepo, pathUseriTxt, userParser);
        copyFromTxt(prieteniiRepo, pathPrieteniiTxt, prietenieParser);
        FileRepository<Long, User> useriFileRepo = new FileRepository<>(new UserValidator(), pathUseriSer);
        FileRepository<Long, Prietenie> prieteniiFileRepo = new FileRepository<>(new PrietenieValidator(), pathPrieteniiSer);
        useriRepo.findAll().forEach(useriFileRepo::save);
        prieteniiRepo.findAll().forEach(prieteniiFileRepo::save);
    }
}
