package domain.validation;
import exceptii.ValidationException;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}