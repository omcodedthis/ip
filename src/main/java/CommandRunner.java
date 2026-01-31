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
     */
    public CommandRunner(Command commandType, String[] commandArguments) {
        this.commandType = commandType;
        this.commandArguments = commandArguments;
        this.isExit = false;
    }

    /**
     * Executes the command specified by the user arguments.
     * Handles commands such as "bye", "list", "mark", and "unmark".
     * If the command is not recognized, it treats the input as a new task description.
     *
     * @param input Raw input string from the user.
     * @param userArguments Array containing the parsed command and arguments.
     * @param taskList List of tasks to be modified or displayed.
     */
    public void runCommand(ArrayList<Task> taskList) {
        int argumentIndex;
        Task task;

        switch (commandType) {
        case BYE:
            System.out.println(OUTPUT_HORIZONTAL_LINE);
            System.out.println(OUTPUT_SNOOPY_HEADER);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(OUTPUT_HORIZONTAL_LINE);
            this.isExit = true;
            break;

        case LIST:
            echoList(taskList);
            break;

        case MARK:
            argumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;
            task = taskList.get(argumentIndex);
            task.markDone();
            System.out.println(OUTPUT_HORIZONTAL_LINE);
            System.out.println(OUTPUT_SNOOPY_HEADER);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.getStatusIcons() + task.getDescription());
            System.out.println(OUTPUT_HORIZONTAL_LINE);
            break;

        case UNMARK:
            argumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;
            task = taskList.get(argumentIndex);
            task.unmarkDone();
            System.out.println(OUTPUT_HORIZONTAL_LINE);
            System.out.println(OUTPUT_SNOOPY_HEADER);
            System.out.println("Alright, I have marked this task as not done yet:");
            System.out.println("[" + task.getStatusIcons() + "] " + task.getDescription());
            System.out.println(OUTPUT_HORIZONTAL_LINE);
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
     * Returns true if the exit command has been issued.
     *
     * @return Exit state of the application.
     */
    public boolean isExit() {
        return this.isExit;
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
            String taskName = taskList.get(i).getDescription();
            String taskStatus = taskList.get(i).getStatusIcons();
            System.out.println("     " + (i + 1) + ". " + taskStatus + taskName);
        }
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

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
        System.out.println(statusIcons + description + " (from: " + event.getFrom() + "to:" + event.getTo() + ")");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }
}