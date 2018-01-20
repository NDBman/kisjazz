package hu.berryweb.kisjazz.validator;

public interface IEmailValidator {

	void checkEmailPattern(String email);
	
	void checkEmailNotExists(String email);
	
	void checkEmailNotBlank(String email);
}
