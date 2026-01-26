import java.util.ArrayList;
import java.util.Scanner;

public class Snoopy {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SNOOPY_OUTPUT_HEADER = "(Snoopy Says)";

    public static void main(String[] args) {
        String logo = "\n ____                                      \n"
                + "/ ___| _ __   ___   ___  _ __  _   _   \n"
                + "\\___ \\| '_ \\ / _ \\ / _ \\| '_ \\| | | |  \n"
                + " ___) | | | | (_) | (_) | |_) | |_| |  \n"
                + "|____/|_| |_|\\___/ \\___/| .__/ \\__, |  \n"
                + "                        |_|    |___/   \n";
        System.out.println("Hello from" + logo);

        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        getCommandAndRun();
    }

    public static void getCommandAndRun() {
        CommandEngine commandEngine = new CommandEngine();
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (!commandEngine.isExit()) {
            System.out.print("Your input: ");
            String input = scanner.nextLine().trim().toLowerCase();

            String[] args = input.split(" ");

            commandEngine.runCommand(input, args, taskList);
        }
        scanner.close();
    }
}
