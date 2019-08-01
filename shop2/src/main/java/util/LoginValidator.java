package util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.User;

public class LoginValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		String group = "error.required";
		if (user.getUserId() == null || user.getUserId().length() == 0) {
			errors.rejectValue("userId", group);
			// group : error.required.UserId
		}
		if (user.getPassword() == null || user.getPassword().length() == 0) {
			errors.rejectValue("password", group);
		}

		if (errors.hasErrors()) {
			errors.reject("error.input.user");
		}
	}
}
