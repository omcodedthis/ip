import java.util.ArrayList;

public class CommandEngine {

    private boolean isExit;
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SNOOPY_OUTPUT_HEADER = "(Snoopy Says)";

    public CommandEngine() {
        this.isExit = false;
    }

    public void runCommand(String input, String[] args, ArrayList<Task> taskList) {
        switch (input) {
        case "bye":
            System.out.println(HORIZONTAL_LINE);
            System.out.println(SNOOPY_OUTPUT_HEADER);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL_LINE);
            isExit = true;
            break;

        case "list":
            echoList(taskList);
            break;

        case "mark":
            int mark_index = Integer.parseInt(args[1]) - 1;
            taskList.get(mark_index).markDone();
            break;

        case "unmark":
            int unmark_index = Integer.parseInt(args[1]) - 1;
            taskList.get(unmark_index).unmarkDone();
            break;

        default:
            addToTaskList(input, taskList);
            echoInput(input);
            break;
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    public static void addToTaskList(String input, ArrayList<Task> taskList) {
        Task task = new Task(input);
        taskList.add(task);
    }

    public static void echoList(ArrayList<Task> taskList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(SNOOPY_OUTPUT_HEADER);

        for (int i = 0; i < taskList.size(); i++) {
            String taskName = taskList.get(i).getDescription();
            String taskStatus = taskList.get(i).getStatusIcon();
            System.out.println("     " + (i + 1) + ". " + "[" + taskStatus + "] " + taskName);
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void echoInput(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(SNOOPY_OUTPUT_HEADER);
        System.out.print("added: ");
        System.out.println(input);
        System.out.println(HORIZONTAL_LINE);
    }

}
