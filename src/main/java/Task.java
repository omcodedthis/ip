/**
 * Represents a task with a description and completion status.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the specified description.
     * The task is initialized as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns "X" if the task is done, otherwise returns a space.
     *
     * @return Status icon string.
     */
    public String getStatusIcons() {
        String statusIcons ="[T]";

        if (isDone) {
            statusIcons += "[X] ";
        } else {
            statusIcons += "[ ] ";
        }

        return statusIcons;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }
}