package snoopy.command;

import snoopy.exception.SnoopyException;
import snoopy.task.Task;
import snoopy.task.TaskList;
import snoopy.ui.Ui;

import java.util.ArrayList;
import java.util.SimpleTimeZone;

/**
 * Handles the execution of user commands and manages the application state.
 */
public class CommandRunner {

    private Command commandType;
    private String[] commandArguments;
    private boolean isExit;

    /**
     * Initializes the command engine.
     * Sets the exit state to false.
     *
     * @param commandType The type of command to be executed.
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
     * @param ui The user interface to handle console outputs.
     * @throws SnoopyException If an invalid task number is provided or an empty list is accessed.
     */
    public void runCommand(TaskList taskList, Ui ui) throws SnoopyException {
        switch (commandType) {
        case BYE:
            setToExit();
            break;
        case LIST:
            if (taskList.getSize() == 0) {
                throw new SnoopyException("Yo dawg, I am not tracking anything!");
            }

            taskList.echoList(ui);
            break;
        case MARK:
            int markArgumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (markArgumentIndex < 0 || markArgumentIndex >= taskList.getSize()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            taskList.setTaskIsDoneValue(markArgumentIndex, true, ui);
            break;
        case UNMARK:
            int unmarkArgumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (unmarkArgumentIndex < 0 || unmarkArgumentIndex >= taskList.getSize()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            taskList.setTaskIsDoneValue(unmarkArgumentIndex, false, ui);
            break;
        case TODO:
            taskList.addToDoToList(commandArguments, ui);
            break;
        case DEADLINE:
            taskList.addDeadlineToList(commandArguments, ui);
            break;
        case EVENT:
            taskList.addEventToList(commandArguments, ui);
            break;
        case DELETE:
            int deleteArgumentIndex = Integer.parseInt(this.commandArguments[1]) - 1;

            if (deleteArgumentIndex < 0 || deleteArgumentIndex >= taskList.getSize()) {
                throw new SnoopyException("Yo dawg, that task number is out of bounds!");
            }
            taskList.deleteFromList(deleteArgumentIndex, ui);
            break;
        case FIND:
            String keyword = this.commandArguments[1].trim().toLowerCase();
            taskList.findTasksWithKeyword(keyword, ui);
            break;
        default:
            break;
        }
    }

    /**
     * Sets isExit to true.
     */
    public void setToExit() {
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