package snoopy.exception;

/**
 * Represents an Exception for when an invalid command error occurs.
 */
public class InvalidCommandException extends SnoopyException {
    public InvalidCommandException(String description) {
        super(description);
    }
}
