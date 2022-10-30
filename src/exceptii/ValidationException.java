package exceptii;

public class ValidationException extends MyException {
    public ValidationException(String message) {
        super("ValidationException: "+message);
    }
}
