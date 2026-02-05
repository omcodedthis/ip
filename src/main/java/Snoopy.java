import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry point for the Snoopy application.
 * Handles the initialization and the main input loop.
 */
public class Snoopy {

    private static final String OUTPUT_HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Entry point of the program.
     * Displays the 'Snoopy' logo and starts the command processing loop.
     *
     * @param args Command line arguments (not used).
     * @throws Exception If an error occurs during the execution of the command loop.
     */
    public static void main(String[] args) throws Exception {
        String snoopyLogo = """
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⡠⠔⢊⣩⣭⡐⠳⣤⣤⣤⡀⠀⠀⠀⠀⠀   
    ⠀⠀⠀⢠⠊⠀⢠⣿⣿⣿⣿⣶⣿⣿⣿⣿⠍⠒⢄⠀   What ya need?
    ⠀⠀⢀⠇⠀⣠⡼⢿⣿⣿⡿⠉⠻⣿⣿⠟⠀⠀⠀⠱⡄      /
    ⠀⢀⢎⣶⣎⡇⠀⠀⠉⠁⠀⠀⠀⠀⣴⣶⣦⠀⠀⠀ ⠃  ___/
    ⢠⣾⢸⣿⣿⣞⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⡆     ____
    ⠻⢹⣸⣿⣿⣿⢰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀ ⡤⠞⠀   / ___| _ __   ___   ___  _ __  _   _
    ⠀⠈⢻⣿⣿⡿⣸⠤⢀⡀⠀⠀⠀⡠⠔⠒⠉⠁⠀⠀⠀   \\___ \\| ._ \\ / _ \\ / _ \\| '_ \\| | | |
    ⡠⢔⠒⡯⣓⣚⣁⣀⣀⣈⣷⣤⣴⣷⠀⠀⠀⠀⠀⠀⠀    ___) | | | | (_) | (_) | |_) | |_| |
    ⠧⡈⠀⡟⠁⠀⠀⠀⠀⠈⠛⠛⠛⠻⢿⡄⡄⠀⠀⠀⠀⠀  |____/|_| |_|\\___/ \\___/| .__/ \\__. |
    ⠰⣇⣰⠛⠦⠤⠤⠤⢤⠀⢀⣺⣿⣿⣿⣧⠀⠀⠀⠀⠀                           |_|    |___/
    ⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⢸⣿⣿⢿⠿⣾⣱⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⡠⠤⢼⡿⠿⠿⠿⠛⠛⠋⠀⡇⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠈⠉⠀⠈⠦⡀⠀⢀⣠⠂⢀⡇⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡤⠒⠛⠀⠈⢅⠠⡈⠑⡀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠀⠀⠀⠈⠀⠁⠈⠀⠀
    """;
        System.out.println(snoopyLogo);

        System.out.println("What can I do for you?");
        System.out.println(OUTPUT_HORIZONTAL_LINE);

        getCommandAndRun();
    }

    /**
     * Captures user input and passes it to the command engine.
     * Continues to prompt for input until the exit command is issued.
     *
     * @throws Exception If an input/output error occurs or a command fails to execute.
     */
    public static void getCommandAndRun() throws Exception {
        boolean isExit = false;
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            System.out.print("Your input: ");
            String input = scanner.nextLine();
            CommandRunner command = InputReader.readInput(input);

            command.runCommand(taskList);

            isExit = command.isExit();
        }
        scanner.close();
    }
}