import java.util.Scanner;

public class Snoopy {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SNOOPY_OUTPUT_HEADER = "(Snoopy Says)";

    public static void echoInput(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(SNOOPY_OUTPUT_HEADER);
        System.out.print("added: ");
        System.out.println(input);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void echoList(String[] userList, int userListCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(SNOOPY_OUTPUT_HEADER);

        for (int i = 0; i < userListCount; i++) {
            System.out.println("     " + (i + 1) + ". " + userList[i]);
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void getCommandAndEcho() {
        String[] userList = new String[100];
        int userListCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Your input: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("bye")) {
                input = "Bye. Hope to see you again soon!";
                echoInput(input);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                echoList(userList, userListCount);
            } else {
                userList[userListCount] = input;
                userListCount++;
                echoInput(input);
            }
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
