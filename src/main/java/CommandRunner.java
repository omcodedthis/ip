import java.util.ArrayList;

/**
 * Handles the execution of user commands and manages the application state.
 */
public class CommandRunner {

    private Command commandType;
    private String[] commandArguments;
    private boolean isExit;
    private static final String OUTPUT_HORIZONTAL_LINE = "____________________________________________________________";
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
    public void runCommand(ArrayList<Task> taskList) {
        int argumentIndex;
        Task task;

        switch (commandType) {
        case BYE:
            setToExit();
            break;
        case LIST:
            echoList(taskList);
            break;
        case MARK:
            argumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;
            setTaskIsDoneValue(argumentIndex, true, taskList);
            break;
        case UNMARK:
            argumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;
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
        System.out.println("Bye. Hope to see you again soon!");
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
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.unmarkDone();
            System.out.println("Alright, I have marked this task as not done yet:");
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

        System.out.println("Here are the tasks in your list:");
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
        System.out.println("Got it. I've added this task:");
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
    public static void addDeadlineToList(String[] commandArguments, ArrayList<Task> taskList) {
        String[] deadlineParts = commandArguments[1].split(" /by ", 2);
        String description = deadlineParts[0];
        String doBy = deadlineParts[1];

        Deadline deadline = new Deadline(description, doBy);
        taskList.add(deadline);
        String statusIcons = deadline.getStatusIcons();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("Got it. I've added this task:");
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
    public static void addEventToList(String[] commandArguments, ArrayList<Task> taskList) {
        String[] partsFrom = commandArguments[1].split(" /from ", 2);
        String description = partsFrom[0];

        String[] partsTo = partsFrom[1].split(" /to ", 2);
        String from = partsTo[0];
        String to = partsTo[1];

        Event event = new Event(description, from, to);
        taskList.add(event);
        String statusIcons = event.getStatusIcons();

        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.println("Got it. I've added this task:");
        System.out.println(statusIcons + description + " (from: " + event.getFrom() + " to: " + event.getTo() + ")");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }
}