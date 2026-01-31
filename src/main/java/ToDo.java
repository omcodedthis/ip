/**
 * Represents a ToDo task.
 * A ToDo task is a task without any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the status icon and type identifier for the ToDo task.
     * Prefixes the status with "[T]" to indicate it is a ToDo task.
     *
     * @return A string containing the task type and the status icon (e.g., "[T][X] ").
     */
    @Override
    public String getStatusIcons() {
        String statusIcons = "[T]";

        if (isDone) {
            statusIcons += "[X] ";
        } else {
            statusIcons += "[ ] ";
        }
        return statusIcons;
    }
}