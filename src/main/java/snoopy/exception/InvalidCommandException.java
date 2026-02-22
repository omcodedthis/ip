package snoopy.exception;

/**
 * Represents an exception for when an invalid command error occurs.
 */
public class InvalidCommandException extends SnoopyException {

    /**
     * Initializes a new InvalidCommandException with the specified description.
     *
     * @param description The detailed message explaining the invalid command error.
     */
    public InvalidCommandException(String description) {
        super(description);
    }
}