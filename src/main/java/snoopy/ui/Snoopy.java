package snoopy.ui;

import snoopy.command.CommandRunner;
import snoopy.command.Parser;
import snoopy.exception.SnoopyException;
import snoopy.storage.Storage;
import snoopy.task.TaskList;

import java.util.Scanner;

/**
 * Main entry point for the Snoopy application.
 * Handles the initialization and the main input loop.
 */
public class Snoopy {

    private final Ui ui;
    private final Scanner scanner;
    private final Storage storageSaver;
    private final TaskList taskList;

    public Snoopy() {
        this.ui = new Ui();
        this.scanner = new Scanner(System.in);
        this.storageSaver = new Storage();

        ui.printStartUpMessage();
        this.taskList = storageSaver.loadFromFile();
        ui.printOutputFooter();
    }

    /**
     * Captures user input and passes it to the command engine.
     * Continues to prompt for input until the exit command is issued.
     *
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.printUserInputPrompt();
                String input = scanner.nextLine();
                CommandRunner command = Parser.readInput(input);

                command.runCommand(taskList, ui);

                storageSaver.saveToFile(taskList);

                isExit = command.isExit();
            } catch (SnoopyException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
        ui.printExitMessage();
        scanner.close();
    }

    public static void main(String[] args) {
        new Snoopy().run();
    }
}
