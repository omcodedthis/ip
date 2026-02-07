package snoopy.exception;

public class InvalidCommandException extends SnoopyException {
    public InvalidCommandException(String description) {
        super(description);
    }
}
