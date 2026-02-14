package snoopy.command;

import snoopy.exception.SnoopyException;
import snoopy.task.Deadline;
import snoopy.task.Event;
import snoopy.task.Task;
import snoopy.task.ToDo;

import java.util.ArrayList;

/**
 * Handles the execution of user commands and manages the application state.
 */
public class CommandRunner {

    private Command commandType;
    private String[] commandArguments;
    private boolean isExit;
    private static final String OUTPUT_HORIZONTAL_LINE =
            "_______________________________________________________________________________________________";
    private static final String OUTPUT_SNOOPY_HEADER = "(Snoopy Says)";

    /**
     * Initializes the command engine.
     * Sets the exit state to false.
     *
     * @param commandType The type of command to be executed (e.g., LIST, TODO).
     * @param commandArguments The full array of parsed command arguments.
     */
    public CommandRunner(Command commandType, String[] commandArguments) {
        this.commandType = commandType;
        this.commandArguments = commandArguments;
        this.isExit = false;
    }

    /**
     * Executes the command specified by the user arguments.
     * Handles commands such as "bye", "list", "mark", and "unmark".
     *
     * @param taskList List of tasks to be modified or displayed.
     */
    public void runCommand(ArrayList<Task> taskList) throws SnoopyException {
        int argumentIndex;
        Task task;

        switch (commandType) {
        case BYE:
            setToExit();
            break;
        case LIST:
            if (taskList.size() == 0) {
                throw new SnoopyException("Yo dawg, I am not tracking anything!");
            }

            echoList(taskList);
            break;
        case MARK:
            argumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (argumentIndex < 0 || argumentIndex >= taskList.size()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            setTaskIsDoneValue(argumentIndex, true, taskList);
            break;
        case UNMARK:
            argumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (argumentIndex < 0 || argumentIndex >= taskList.size()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            setTaskIsDoneValue(argumentIndex, false, taskList);
            break;
        case TODO:
            addToDoToList(commandArguments, taskList);
            break;
        case DEADLINE:
            addDeadlineToList(commandArguments, taskList);
            break;
        case EVENT:
            addEventToList(commandArguments, taskList);
            break;
        case DELETE:
            argumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (argumentIndex < 0 || argumentIndex >= taskList.size()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            deleteFromList(argumentIndex, taskList);
            break;
        default:
            break;
        }
    }

    /**
     * Prints the 'BYE' message.
     * Sets isExit to true.
     */
    public void setToExit() {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("Ciao! See ya later.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);

        this.isExit = true;
    }

    /**
     * Returns true if the exit command has been issued.
     *
     * @return Exit state of the application.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Updates the completion status of a specific task and prints the result.
     * Can mark a task as either done or not done based on the isDone flag.
     *
     * @param index The 0-based index of the task in the taskList to modify.
     * @param isDone True to mark the task as done, false to mark it as not done.
     * @param taskList The list of tasks containing the target task.
     */
    public void setTaskIsDoneValue(int index, boolean isDone, ArrayList<Task>taskList) {
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
     * Prints the current list of tasks to the console.
     *
     * @param taskList List of tasks to print.
     */
    public static void echoList(ArrayList<Task> taskList) {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);

        System.out.println("Here is everything I am tracking dawg:");
        for (int i = 0; i < taskList.size(); i++) {
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
     * Creates and adds a ToDo task to the task list.
     * Parses the description from the command arguments and displays the success message.
     *
     * @param commandArguments Array containing the command and its arguments.
     * @param taskList The list of tasks to add the new ToDo to.
     */
    public static void addToDoToList(String[] commandArguments, ArrayList<Task> taskList) {
        String description = commandArguments[1];
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        String statusIcons = todo.getStatusIcons();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("No problemo. I have added this ToDo to the list:");
        System.out.println(statusIcons + description);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Creates and adds a Deadline task to the task list.
     * Splits the arguments to extract the description and the 'by' date.
     *
     * @param commandArguments Array containing the command and its arguments.
     * @param taskList The list of tasks to add the new Deadline to.
     */
    public static void addDeadlineToList(String[] commandArguments, ArrayList<Task> taskList) throws SnoopyException {
        String[] deadlineParts = commandArguments[1].split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new SnoopyException("Yo dawg, you cannot have an empty description or date!");
        }

        String description = deadlineParts[0];
        String doBy = deadlineParts[1];

        Deadline deadline = new Deadline(description, doBy);
        taskList.add(deadline);
        String statusIcons = deadline.getStatusIcons();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("No problemo. I have added this Deadline to the list:");
        System.out.println(statusIcons + description + " (by: " + deadline.getDoBy() + ")");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Creates and adds an Event task to the task list.
     * Splits the arguments to extract the description, 'from' date, and 'to' date.
     *
     * @param commandArguments Array containing the command and its arguments.
     * @param taskList The list of tasks to add the new Event to.
     */
    public static void addEventToList(String[] commandArguments, ArrayList<Task> taskList) throws SnoopyException {
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
        taskList.add(event);
        String statusIcons = event.getStatusIcons();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("No problemo. I have added this Event to the list:");
        System.out.println(statusIcons + description + " (from: " + event.getFrom() + " to: " + event.getTo() + ")");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Remove the given task index from taskList.
     * Can mark a task as either done or not done based on the isDone flag.
     *
     * @param index The 0-based index of the task in the taskList to delete.
     * @param taskList The list of tasks containing the target task.
     */
    public static void deleteFromList(int index, ArrayList<Task> taskList) {
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