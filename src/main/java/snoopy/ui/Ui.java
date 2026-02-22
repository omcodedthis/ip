package snoopy.ui;

import snoopy.task.Deadline;
import snoopy.task.Event;
import snoopy.task.Task;
import snoopy.task.TaskList;

/**
 * Handles the user interface of the application, including formatting and printing messages to the console.
 */
public class Ui {
    private static final String OUTPUT_HORIZONTAL_LINE =
            "_______________________________________________________________________________________________";
    private static final String OUTPUT_SNOOPY_HEADER = "(Snoopy Says)";
    private static final String OUTPUT_SNOOPY_STARTUP_LOG = "(Snoopy Startup Log - Reports Blank If No Issues)";

    /**
     * Initializes the user interface.
     */
    public Ui() {}

    /**
     * Prints the startup logo and initial welcome messages.
     */
    public void printStartUpMessage() {
        String logo = "\n ____                                      \n"
                + "/ ___| _ __   ___   ___  _ __  _   _   \n"
                + "\\___ \\| '_ \\ / _ \\ / _ \\| '_ \\| | | |  \n"
                + " ___) | | | | (_) | (_) | |_) | |_| |  \n"
                + "|____/|_| |_|\\___/ \\___/| .__/ \\__, |  \n"
                + "                        |_|    |___/   \n";
        System.out.println("Hello from" + logo);
        System.out.println("What can I do for you dawg?");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_STARTUP_LOG);
    }

    /**
     * Prints the prompt for user input.
     */
    public void printUserInputPrompt() {
        System.out.print("Your input: ");
    }

    /**
     * Prints the standard header for Snoopy's outputs, including a horizontal line.
     */
    public void printOutputHeader() {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
    }

    /**
     * Prints the standard footer for Snoopy's outputs, consisting of a horizontal line.
     */
    public void printOutputFooter() {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Prints an error message wrapped in the standard output formatting.
     *
     * @param message The error message to be printed.
     */
    public void printErrorMessage(String message) {
        printOutputHeader();
        System.out.println(message);
        printOutputFooter();
    }

    /**
     * Prints the exit message when the application is closing.
     */
    public void printExitMessage() {
        printOutputHeader();
        System.out.println("Ciao! See ya later.");
        printOutputFooter();
    }

    /**
     * Prints the confirmation message for marking a task as done.
     */
    public void printMarkDoneMessage() {
        System.out.println("Marked this as done dawg:");
    }

    /**
     * Prints the confirmation message for marking a task as not done.
     */
    public void printUnMarkDoneMessage() {
        System.out.println("Marked this not done dawg, you gotta double up:");
    }

    /**
     * Prints the details of a specific task.
     *
     * @param task The task to be printed.
     */
    public void printTask(Task task) {
        System.out.println(task.getStatusIcons() + task.getDescription());
    }

    public void printOutList(TaskList taskList) {
        for (int i = 0; i < taskList.getSize(); i++) {
            Task currentTask = taskList.getTaskFromIndex(i);

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
    }

    /**
     * Prints all tasks currently stored in the provided task list.
     *
     * @param taskList The task list containing the tasks to be printed.
     */
    public void printListFromTaskList(TaskList taskList) {
        printOutputHeader();

        System.out.println("Here is everything I am tracking dawg:");
        printOutList(taskList);
        printOutputFooter();
    }

    /**
     * Prints the confirmation message after successfully adding a ToDo task.
     *
     * @param statusIcons The status icons of the task.
     * @param description The description of the task.
     * @param currentSize The total number of tasks in the list after addition.
     */
    public void printAddToDoMessage(String statusIcons, String description, int currentSize) {
        printOutputHeader();
        System.out.println("No problemo. I have added this ToDo to the list:");
        System.out.println(statusIcons + description);
        System.out.println("Now you have " + currentSize + " task(s) in the list.");
        printOutputFooter();
    }

    /**
     * Prints the confirmation message after successfully adding a Deadline task.
     *
     * @param statusIcons The status icons of the task.
     * @param description The description of the task.
     * @param doBy The deadline date or time.
     * @param currentSize The total number of tasks in the list after addition.
     */
    public void printAddDeadlineMessage(String statusIcons, String description, String doBy, int currentSize) {
        printOutputHeader();
        System.out.println("No problemo. I have added this Deadline to the list:");
        System.out.println(statusIcons + description + " (by: " + doBy + ")");
        System.out.println("Now you have " + currentSize + " task(s) in the list.");
        printOutputFooter();
    }

    /**
     * Prints the confirmation message after successfully adding an Event task.
     *
     * @param statusIcons The status icons of the task.
     * @param description The description of the task.
     * @param from The start date or time of the event.
     * @param to The end date or time of the event.
     * @param currentSize The total number of tasks in the list after addition.
     */
    public void printAddEventMessage(String statusIcons, String description, String from, String to, int currentSize) {
        printOutputHeader();
        System.out.println("No problemo. I have added this Event to the list:");
        System.out.println(statusIcons + description + " (from: " + from + " to: " + to + ")");
        System.out.println("Now you have " + currentSize + " task(s) in the list.");
        printOutputFooter();
    }

    /**
     * Prints the confirmation message showing the task details before it is deleted.
     *
     * @param taskType The type of the task (e.g., ToDo, Deadline, Event).
     * @param statusIcons The status icons of the task.
     * @param description The description of the task.
     */
    public void printBeforeDeleteMessage(String taskType, String statusIcons, String description) {
        printOutputHeader();
        System.out.println("No problemo. I will delete this " + taskType + " from the list:");
        System.out.println(statusIcons + description);
    }

    /**
     * Prints the remaining number of tasks in the list after a deletion.
     *
     * @param currentSize The total number of tasks in the list after deletion.
     */
    public void printAfterDeleteMessage(int currentSize) {
        System.out.println("Now you have " + currentSize + " task(s) in the list.");
        printOutputFooter();
    }

    public void printNoFoundTasksMessage(String keyword) {
        printOutputHeader();
        System.out.println("No tasks with the keyword of " + keyword + " were found!");
        printOutputFooter();
    }

    public void printFoundTasksList(TaskList foundTasks) {
        printOutputHeader();

        System.out.println("Here is everything I found dawg:");
        printOutList(foundTasks);
        printOutputFooter();
    }
}
