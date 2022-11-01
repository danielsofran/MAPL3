package domain.validation;

import domain.Prietenie;
import exceptii.ValidationException;

public class PrietenieValidator implements Validator<Prietenie> {
    private final UserValidator userValidator;

    public PrietenieValidator() {
        this.userValidator = new UserValidator();
    }

    public PrietenieValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    /**
     * Valideaza o prietenie
     * @param entity - prietenia de validat
     * @throws ValidationException - daca prietenia nu este valida
     */
    @Override
    public void validate(Prietenie entity) throws ValidationException {
        if (entity == null)
            throw new ValidationException("Prietenia nu poate fi vida!");
        if (entity.getFirst() == null || entity.getSecond() == null)
            throw new ValidationException("Prietenia trebuie sa aiba cel putin un user!");
        if (entity.getFirst().equals(entity.getSecond()))
            throw new ValidationException("Un user nu poate fi prieten cu el insusi!");
        userValidator.validate(entity.getFirst());
        userValidator.validate(entity.getSecond());
    }
}