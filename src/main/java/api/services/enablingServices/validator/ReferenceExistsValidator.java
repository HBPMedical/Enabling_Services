package api.services.enablingServices.validator;

import api.services.enablingServices.annotation.ReferenceExists;
import api.services.enablingServices.component.ApplicationContextProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@Component
public class ReferenceExistsValidator implements ConstraintValidator<ReferenceExists, Object> {

    JpaRepository repository;

    @Override
    public void initialize(ReferenceExists referenceExists) {
        this.repository = (JpaRepository) ApplicationContextProvider.getApplicationContext().getBean(referenceExists.repository());
        String repo = repository.toString();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null)
            return false;

        return this.repository.findById((UUID) o).isPresent();
    }
}