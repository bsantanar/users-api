package bsantanar.com.usersapi.exception;

public class EmailAlreadyExistsException  extends RuntimeException{
    public EmailAlreadyExistsException() {
        super("Correo ya existe");
    }
}
