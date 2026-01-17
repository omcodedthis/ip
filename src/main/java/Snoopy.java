public class Snoopy {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

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

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
