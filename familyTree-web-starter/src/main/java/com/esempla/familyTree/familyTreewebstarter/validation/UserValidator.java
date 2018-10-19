package com.esempla.familyTree.familyTreewebstarter.validation;

import com.esempla.familyTree.familyTreedata.domain.User;
import com.esempla.familyTree.familyTreedata.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String userName = user.getUserName();
        User userByLogin = userService.findUserByUserName(userName);
        if (userByLogin != null) {
            errors.rejectValue("userName",
                    "userName.exists",
                    new Object[]{userName},
                    "User name " + userName + " already in use");
        }
        String password = user.getPassword();
        if (password == null || password.isEmpty()) {
            errors.rejectValue("password", "password.Empty", new Object[]{password}, "Password  is required");
        } else {
            if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$")) {
                errors.rejectValue("password",
                        "password.Pattern",
                        new Object[]{password},
                        "Password  is required to be minimum six characters, at least one letter and one number.");
            }
            if (password.length() < 6 || password.length() > 10) {
                errors.rejectValue("password",
                        "password.Size",
                        new Object[]{password},
                        "Password  size is required to be between 6 and 10 characters.");
            }

            String confirmPassword = user.getConfirmPassword();
            if (confirmPassword == null || confirmPassword.isEmpty()) {
                errors.rejectValue("confirmPassword", "confirmPassword.Empty", new Object[]{confirmPassword}, "ConfirmPassword is required");
            }
            if (confirmPassword != null && !confirmPassword.equals(password)) {
                errors.rejectValue("confirmPassword", "confirmPassword.NotEqual", new Object[]{confirmPassword}, "Password and ConfirmPassword not matches");
            }
        }

    }

}
