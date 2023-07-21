package	edu.Roma42.exceptions;

public class	AlreadyAuthenticatedException extends RuntimeException {
	public	AlreadyAuthenticatedException(String errorMessage) {
		super(errorMessage);
	}
}
