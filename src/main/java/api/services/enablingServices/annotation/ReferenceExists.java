package api.services.enablingServices.annotation;

import api.services.enablingServices.validator.ReferenceExistsValidator;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ReferenceExistsValidator.class)
@Documented
public @interface ReferenceExists {
    String message() default "{reference.exists.violation}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends JpaRepository> repository();
}