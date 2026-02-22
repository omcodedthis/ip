package snoopy.command;

import snoopy.exception.SnoopyException;
import snoopy.task.Deadline;
import snoopy.task.Event;
import snoopy.task.Task;
import snoopy.task.TaskList;
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
    public void runCommand(TaskList taskList) throws SnoopyException {
        switch (commandType) {
        case BYE:
            setToExit();
            break;
        case LIST:
            if (taskList.getSize() == 0) {
                throw new SnoopyException("Yo dawg, I am not tracking anything!");
            }

            taskList.echoList();
            break;
        case MARK:
            int markArgumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (markArgumentIndex < 0 || markArgumentIndex >= taskList.getSize()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            taskList.setTaskIsDoneValue(markArgumentIndex, true);
            break;
        case UNMARK:
            int unmarkArgumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (unmarkArgumentIndex < 0 || unmarkArgumentIndex >= taskList.getSize()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            taskList.setTaskIsDoneValue(unmarkArgumentIndex, false);
            break;
        case TODO:
            taskList.addToDoToList(commandArguments);
            break;
        case DEADLINE:
            taskList.addDeadlineToList(commandArguments);
            break;
        case EVENT:
            taskList.addEventToList(commandArguments);
            break;
        case DELETE:
            int deleteArgumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (deleteArgumentIndex < 0 || deleteArgumentIndex >= taskList.getSize()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            taskList.deleteFromList(deleteArgumentIndex);
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
}