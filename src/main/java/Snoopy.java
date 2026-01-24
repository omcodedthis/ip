import java.util.Scanner;

public class Snoopy {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void echoInput(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Snoopy says:");
        System.out.println(input);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void getCommandAndEcho() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Your input: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("bye")) {
                input = "Bye. Hope to see you again soon!";
                echoInput(input);
                break;
            }
            echoInput(input);
        }
        scanner.close();
    }

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

        getCommandAndEcho();
    }
}
