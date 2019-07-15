package api.services.enablingServices.validator;

import api.services.enablingServices.annotation.Unique;
import api.services.enablingServices.component.ApplicationContextProvider;
import api.services.enablingServices.service.FieldValueExists;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    FieldValueExists service;
    private String columnName;

    @Override
    public void initialize(Unique unique) {
        this.service = (FieldValueExists) ApplicationContextProvider.getApplicationContext().getBean(unique.service());
        this.columnName = unique.columnName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return !this.service.fieldValueExists(o, this.columnName);
    }
}