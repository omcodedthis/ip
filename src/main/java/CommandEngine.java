import java.util.ArrayList;

/**
 * Handles the execution of user commands and manages the application state.
 */
public class CommandEngine {

    private boolean isExit;
    private static final String OUTPUT_HORIZONTAL_LINE = "____________________________________________________________";
    private static final String OUTPUT_SNOOPY_HEADER = "(Snoopy Says)";

    /**
     * Initializes the command engine.
     * Sets the exit state to false.
     */
    public CommandEngine() {
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
    public void runCommand(String input, String[] userArguments, ArrayList<Task> taskList) {
        String command = userArguments[0];

        switch (command) {
        case "bye":
            System.out.println(OUTPUT_HORIZONTAL_LINE);
            System.out.println(OUTPUT_SNOOPY_HEADER);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(OUTPUT_HORIZONTAL_LINE);
            this.isExit = true;
            break;

        case "list":
            echoList(taskList);
            break;

        case "mark":
            int mark_index = Integer.parseInt(userArguments[1]) - 1;
            taskList.get(mark_index).markDone();
            break;

        case "unmark":
            int unmark_index = Integer.parseInt(userArguments[1]) - 1;
            taskList.get(unmark_index).unmarkDone();
            break;

        default:
            addToTaskList(input, taskList);
            echoInput(input);
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
     * Adds a new task to the task list.
     *
     * @param input Description of the task to add.
     * @param taskList List to add the task to.
     */
    public static void addToTaskList(String input, ArrayList<Task> taskList) {
        Task task = new Task(input);
        taskList.add(task);
    }

    /**
     * Prints the current list of tasks to the console.
     *
     * @param taskList List of tasks to print.
     */
    public static void echoList(ArrayList<Task> taskList) {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);

        for (int i = 0; i < taskList.size(); i++) {
            String taskName = taskList.get(i).getDescription();
            String taskStatus = taskList.get(i).getStatusIcon();
            System.out.println("     " + (i + 1) + ". " + "[" + taskStatus + "] " + taskName);
        }

        System.out.println();
    }

    /**
     * Prints a confirmation message indicating a task has been added.
     *
     * @param input Description of the added task.
     */
    public static void echoInput(String input) {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_SNOOPY_HEADER);
        System.out.print("added: ");
        System.out.println(input);
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }
}