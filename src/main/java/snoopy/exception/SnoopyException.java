package snoopy.exception;

/**
 * Represents an Exception for when incorrect input is passed to Snoopy.
 */
public class SnoopyException extends Exception {
    public SnoopyException(String description) {
        super(description);
    }
}
