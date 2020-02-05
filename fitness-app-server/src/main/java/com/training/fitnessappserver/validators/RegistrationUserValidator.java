package com.training.fitnessappserver.validators;

import com.training.fitnessappserver.dto.RegisterRequestModel;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class RegistrationUserValidator implements Validator {
    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    private static final int MINIMUM_LOGIN_LENGTH = 4;

    UserRepository userRepository;

    @Autowired
    public RegistrationUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterRequestModel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field required");

        RegisterRequestModel registrationModel = (RegisterRequestModel) o;

        if (registrationModel.getPassword() != null && registrationModel.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH) {
            errors.rejectValue("password", "field.min.length",
                    new Object[]{Integer.valueOf(MINIMUM_PASSWORD_LENGTH)},
                    "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
        }

        if (registrationModel.getLogin() != null && registrationModel.getLogin().trim().length() < MINIMUM_LOGIN_LENGTH) {
            errors.rejectValue("login", "field.min.length",
                    new Object[]{Integer.valueOf(MINIMUM_LOGIN_LENGTH)},
                    "The login must be at least [" + MINIMUM_LOGIN_LENGTH + "] characters in length.");
        }

        try {

        } catch (ItemNotFoundException ex) {

        }

        if (userRepository.findByLogin(registrationModel.getLogin()).isPresent()) {
            errors.rejectValue("login", "value.negative", "User with login - " + registrationModel.getLogin() + " already exists.");
        }
    }
}
