/**
 * Represents an Event task.
 * An Event task corresponds to a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from The start time/date of the event.
     * @param to The end time/date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the status icon and type identifier for the Event task.
     * Prefixes the status with "[E]" to indicate it is an Event task.
     *
     * @return A string containing the task type and the status icon (e.g., "[E][X] ").
     */
    @Override
    public String getStatusIcons() {
        String statusIcons = "[E]";

        if (isDone) {
            statusIcons += "[X] ";
        } else {
            statusIcons += "[ ] ";
        }
        return statusIcons;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time string.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time string.
     */
    public String getTo() {
        return this.to;
    }
}