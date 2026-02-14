package snoopy.ui;

import snoopy.command.CommandRunner;
import snoopy.command.InputReader;
import snoopy.exception.SnoopyException;
import snoopy.task.Task;
import snoopy.storage.SnoopyStorage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry point for the Snoopy application.
 * Handles the initialization and the main input loop.
 */
public class Snoopy {

    private static final String OUTPUT_HORIZONTAL_LINE =
            "_______________________________________________________________________________________________";
    private static final String OUTPUT_SNOOPY_HEADER = "(Snoopy Says)";
    private static final String OUTPUT_SNOOPY_STARTUP_LOG = "(Snoopy Startup Log - Reports Blank If No Issues)";

    /**
     * Entry point of the program.
     * Displays the 'Snoopy' logo and starts the command processing loop.
     *
     * @param args Command line arguments (not used).
     * @throws Exception If an error occurs during the execution of the command loop.
     */
    public static void main(String[] args) throws Exception {
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

        SnoopyStorage snoopyStorageSaver = new SnoopyStorage();
        ArrayList<Task> taskList = snoopyStorageSaver.loadFromFile();
        System.out.println(OUTPUT_HORIZONTAL_LINE);

        getCommandAndRun(taskList, snoopyStorageSaver);
    }

    /**
     * Captures user input and passes it to the command engine.
     * Continues to prompt for input until the exit command is issued.
     *
     */
    public static void getCommandAndRun(ArrayList<Task> taskList, SnoopyStorage snoopyStorageSaver) {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                System.out.print("Your input: ");
                String input = scanner.nextLine();
                CommandRunner command = InputReader.readInput(input);

                command.runCommand(taskList);

                snoopyStorageSaver.saveToFile(taskList);

                isExit = command.isExit();
            } catch (SnoopyException e) {
                System.out.println(OUTPUT_HORIZONTAL_LINE);
                System.out.println(OUTPUT_SNOOPY_HEADER);
                System.out.println(e.getMessage());
                System.out.println(OUTPUT_HORIZONTAL_LINE);
            }
        }
        scanner.close();
    }
}
