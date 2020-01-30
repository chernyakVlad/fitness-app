package com.training.fitnessappserver.validators;

import com.training.fitnessappserver.dto.RegisterRequestModel;
import com.training.fitnessappserver.entity.user.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterRequestModel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;
    }
}
