package exceptii;

public class NotExistentException extends RepositoryException {
    public NotExistentException(String message) {
        super(message);
    }
}
