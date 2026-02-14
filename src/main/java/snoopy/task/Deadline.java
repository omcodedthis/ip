package snoopy.task;

/**
 * Represents a Deadline task.
 * A Deadline task corresponds to a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected String doBy;

    /**
     * Creates a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param doBy The date/time by which the task must be completed.
     */
    public Deadline(String description, String doBy) {
        super(description);
        this.doBy = doBy;
    }

    /**
     * Returns the status icon and type identifier for the Deadline task.
     * Prefixes the status with "[D]" to indicate it is a Deadline task.
     *
     * @return A string containing the task type and the status icon (e.g., "[D][X] ").
     */
    @Override
    public String getStatusIcons() {
        String statusIcons = "[D]";

        if (isDone) {
            statusIcons += "[X] ";
        } else {
            statusIcons += "[ ] ";
        }
        return statusIcons;
    }

    /**
     * Returns the deadline string associated with this task.
     *
     * @return The deadline date/time string.
     */
    public String getDoBy() {
        return doBy;
    }

    @Override
    public String toStringForStorage() {
        return "D | " + super.toStringForStorage() + " | " + this.doBy;
    }
}