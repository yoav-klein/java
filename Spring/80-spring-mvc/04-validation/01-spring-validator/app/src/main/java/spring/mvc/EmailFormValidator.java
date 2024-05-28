package spring.mvc;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmailFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EmailForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmailForm emailForm = (EmailForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
        if (emailForm.getEmail() != null && !emailForm.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.rejectValue("email", "email.invalid");
        }
    }
}