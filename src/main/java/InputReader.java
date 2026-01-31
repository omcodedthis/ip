/**
 * Handles the parsing of user input.
 * Converts raw input strings into executable CommandRunner objects.
 */
public class InputReader {

    /**
     * Parses the user's raw input string and returns the corresponding CommandRunner with the correct command type.
     * Identifies the command type and passes the arguments to the runner.
     *
     * @param input The raw input string entered by the user.
     * @return A CommandRunner configured with the correct command type and input arguments.
     * @throws Exception If the command is not recognized (invalid command).
     */
    public static CommandRunner readInput(String input) throws Exception {
        String[] commandArguments = input.trim().split(" ", 2);
        Command commandType = Command.valueOf(commandArguments[0].toUpperCase());

        switch (commandType) {
        case BYE:
            return new CommandRunner(Command.BYE, commandArguments);

        case LIST:
            return new CommandRunner(Command.LIST, commandArguments);

        case MARK:
            return new CommandRunner(Command.MARK, commandArguments);

        case UNMARK:
            return new CommandRunner(Command.UNMARK, commandArguments);

        case TODO:
            return new CommandRunner(Command.TODO, commandArguments);

        case DEADLINE:
            return new CommandRunner(Command.DEADLINE, commandArguments);

        case EVENT:
            return new CommandRunner(Command.EVENT, commandArguments);

        default:
            throw new Exception("Unknown command!");
        }
    }
}