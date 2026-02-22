package snoopy.task;

import snoopy.exception.SnoopyException;
import snoopy.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks and handles operations such as adding, deleting, and displaying tasks.
 */
public class TaskList {

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
     * @param ui The user interface to handle console outputs.
     */
    public void setTaskIsDoneValue(int index, boolean isDone, Ui ui) {
        Task task = taskList.get(index);
        ui.printOutputHeader();

        if (isDone) {
            task.markDone();
            ui.printMarkDoneMessage();
        } else {
            task.unmarkDone();
            ui.printUnMarkDoneMessage();
        }

        ui.printTask(task);
        ui.printOutputFooter();
    }

    /**
     * Prints all currently tracked tasks to the console.
     * * @param ui The user interface to handle console outputs.
     */
    public void echoList(Ui ui) {
        ui.printListFromTaskList(this);
    }

    /**
     * Adds a new ToDo task to the list and prints a confirmation.
     *
     * @param commandArguments Array containing the command and the task description.
     * @param ui The user interface to handle console outputs.
     */
    public void addToDoToList(String[] commandArguments, Ui ui) {
        String description = commandArguments[1];
        ToDo todo = new ToDo(description);
        this.addTasktoList(todo);
        String statusIcons = todo.getStatusIcons();

        ui.printAddToDoMessage(statusIcons, description, this.getSize());
    }

    /**
     * Adds a new Deadline task to the list and prints a confirmation.
     *
     * @param commandArguments Array containing the command, description, and deadline date.
     * @param ui The user interface to handle console outputs.
     * @throws SnoopyException If the description or date is empty.
     */
    public void addDeadlineToList(String[] commandArguments, Ui ui) throws SnoopyException {
        String[] deadlineParts = commandArguments[1].split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new SnoopyException("Yo dawg, you cannot have an empty description or date!");
        }

        String description = deadlineParts[0];
        String doBy = deadlineParts[1];

        Deadline deadline = new Deadline(description, doBy);
        this.addTasktoList(deadline);
        String statusIcons = deadline.getStatusIcons();

        ui.printAddDeadlineMessage(statusIcons, description, doBy, this.getSize());
    }

    /**
     * Adds a new Event task to the list and prints a confirmation.
     *
     * @param commandArguments Array containing the command, description, start time, and end time.
     * @param ui The user interface to handle console outputs.
     * @throws SnoopyException If the description, start time, or end time is empty or missing.
     */
    public void addEventToList(String[] commandArguments, Ui ui) throws SnoopyException {
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

        ui.printAddEventMessage(statusIcons, description, from, to, this.getSize());
    }

    /**
     * Deletes the task at the specified index from the list and prints a confirmation.
     *
     * @param index Index of the task to be deleted.
     * @param ui The user interface to handle console outputs.
     */
    public void deleteFromList(int index, Ui ui) {
        Task task = taskList.get(index);
        String taskType = task.getClass().getSimpleName();
        String statusIcons = task.getStatusIcons();
        String description = task.getDescription();

        ui.printBeforeDeleteMessage(taskType, statusIcons, description);
        taskList.remove(index);
        ui.printAfterDeleteMessage(this.getSize());
    }

    /**
     * Finds tasks containing the specified keyword in their description and prints the results.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @param ui The user interface to handle console outputs.
     */
    public void findTasksWithKeyword(String keyword, Ui ui) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < this.getSize(); i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.getDescription().toLowerCase().contains(keyword)) {
                foundTasks.addTasktoList(currentTask);
            }
        }

        if (foundTasks.getSize() == 0) {
            ui.printNoFoundTasksMessage(keyword);
        } else {
            ui.printFoundTasksList(foundTasks);
        }
    }
}
