package snoopy.exception;

/**
 * Represents an exception for when incorrect input is provided.
 */
public class SnoopyException extends Exception {

    /**
     * Initializes a new SnoopyException with the specified description.
     *
     * @param description The detailed message explaining the error.
     */
    public SnoopyException(String description) {
        super(description);
    }
}
