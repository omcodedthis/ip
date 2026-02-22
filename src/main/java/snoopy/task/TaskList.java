package snoopy.task;

import snoopy.exception.SnoopyException;

import java.util.ArrayList;

/**
 * Represents a list of tasks and handles operations such as adding, deleting, and displaying tasks.
 */
public class TaskList {
    private static final String OUTPUT_HORIZONTAL_LINE =
            "_______________________________________________________________________________________________";
    private static final String OUTPUT_SNOOPY_HEADER = "(Snoopy Says)";

    private ArrayList<Task> taskList;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTasktoList(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the task located at the specified index.
     *
     * @param index Index of the targeted task.
     * @return Targeted task.
     */
    public Task getTaskFromIndex(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return Total number of tasks.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Updates the completion status of a task at the specified index and prints a confirmation.
     *
     * @param index Index of the task to update.
     * @param isDone Boolean representing whether the task is completed.
     */
    public void setTaskIsDoneValue(int index, boolean isDone) {
        Task task = taskList.get(index);
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);

        if (isDone) {
            task.markDone();
            System.out.println("Marked this as done dawg:");
        } else {
            task.unmarkDone();
            System.out.println("Marked this not done dawg, you gotta double up:");
        }

        System.out.println(task.getStatusIcons() + task.getDescription());
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Prints all currently tracked tasks to the console.
     */
    public void echoList() {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);

        System.out.println("Here is everything I am tracking dawg:");
        for (int i = 0; i < this.getSize(); i++) {
            Task currentTask = taskList.get(i);

            String currentTaskName = currentTask.getDescription();
            String currentTaskStatus = currentTask.getStatusIcons();
            System.out.print("     " + (i + 1) + ". " + currentTaskStatus + currentTaskName);

            if (currentTask instanceof Deadline deadline) {
                System.out.println(" (by: " + deadline.getDoBy() + ")");
            } else if (currentTask instanceof Event event) {
                System.out.println(" (from: " + event.getFrom() + " to: " + event.getTo() + ")");
            } else {
                System.out.println();
            }
        }
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Adds a new ToDo task to the list and prints a confirmation.
     *
     * @param commandArguments Array containing the command and the task description.
     */
    public void addToDoToList(String[] commandArguments) {
        String description = commandArguments[1];
        ToDo todo = new ToDo(description);
        this.addTasktoList(todo);
        String statusIcons = todo.getStatusIcons();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("No problemo. I have added this ToDo to the list:");
        System.out.println(statusIcons + description);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Adds a new Deadline task to the list and prints a confirmation.
     *
     * @param commandArguments Array containing the command, description, and deadline date.
     * @throws SnoopyException If the description or date is empty.
     */
    public void addDeadlineToList(String[] commandArguments) throws SnoopyException {
        String[] deadlineParts = commandArguments[1].split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new SnoopyException("Yo dawg, you cannot have an empty description or date!");
        }

        String description = deadlineParts[0];
        String doBy = deadlineParts[1];

        Deadline deadline = new Deadline(description, doBy);
        this.addTasktoList(deadline);
        String statusIcons = deadline.getStatusIcons();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("No problemo. I have added this Deadline to the list:");
        System.out.println(statusIcons + description + " (by: " + deadline.getDoBy() + ")");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Adds a new Event task to the list and prints a confirmation.
     *
     * @param commandArguments Array containing the command, description, start time, and end time.
     * @throws SnoopyException If the description, start time, or end time is empty or missing.
     */
    public void addEventToList(String[] commandArguments) throws SnoopyException {
        String[] partsFrom = commandArguments[1].split(" /from ", 2);

        if (partsFrom.length < 2 || partsFrom[0].trim().isEmpty() || partsFrom[1].trim().isEmpty()) {
            throw new SnoopyException("Yo dawg, you need a description and a /from date!");
        }
        String description = partsFrom[0].trim();

        String[] partsTo = partsFrom[1].split(" /to ", 2);

        if (partsTo.length < 2 || partsTo[0].trim().isEmpty() || partsTo[1].trim().isEmpty()) {
            throw new SnoopyException("Yo dawg, ensure you have both /from and /to times!");
        }

        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        Event event = new Event(description, from, to);
        this.addTasktoList(event);
        String statusIcons = event.getStatusIcons();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("No problemo. I have added this Event to the list:");
        System.out.println(statusIcons + description + " (from: " + event.getFrom() + " to: " + event.getTo() + ")");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Deletes the task at the specified index from the list and prints a confirmation.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteFromList(int index) {
        Task task = taskList.get(index);
        String taskType = task.getClass().getSimpleName();
        String statusIcons = task.getStatusIcons();
        String description = task.getDescription();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("No problemo. I will delete this " + taskType + " from the list:");
        System.out.println(statusIcons + description);
        taskList.remove(index);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }
}