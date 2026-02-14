package snoopy.command;

import snoopy.exception.InvalidCommandException;
import snoopy.exception.SnoopyException;

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
    public static CommandRunner readInput(String input) throws SnoopyException {
        // Check if input is empty
        if (input.trim().isEmpty()) {
            throw new InvalidCommandException("Yo dawg, say something!");
        }

        // Checks if first word from input is a valid command
        String[] commandArguments = input.trim().split(" ", 2);
        Command commandType;
        try {
            commandType = Command.valueOf(commandArguments[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Yo dawg, I got no idea what " + input + " means!");
        }

        long commandArugmentsLength = commandArguments.length;
        boolean isInvalid;

        switch (commandType) {
        case BYE:
            if (commandArugmentsLength != 1) {
                throw new SnoopyException("Yo dawg, you should say this: bye");
            }
            return new CommandRunner(Command.BYE, commandArguments);
        case LIST:
            if (commandArugmentsLength != 1) {
                throw new SnoopyException("Yo dawg, you should say this: list");
            }
            return new CommandRunner(Command.LIST, commandArguments);
        case MARK:
            if (commandArugmentsLength < 2) {
                throw new SnoopyException("Yo dawg, you should tell me the task number!");
            }
            return new CommandRunner(Command.MARK, commandArguments);
        case UNMARK:
            if (commandArugmentsLength < 2) {
                throw new SnoopyException("Yo dawg, you should tell me the task number!");
            }
            return new CommandRunner(Command.UNMARK, commandArguments);
        case TODO:
            isInvalid = (commandArugmentsLength < 2) || (commandArguments[1].trim().isEmpty());

            if (isInvalid) {
                throw new SnoopyException("Yo dawg, you should say this: todo <insert description>");
            }
            return new CommandRunner(Command.TODO, commandArguments);
        case DEADLINE:
            isInvalid = (commandArugmentsLength < 2) || !(commandArguments[1].contains(" /by "));

            if (isInvalid) {
                throw new SnoopyException("Yo dawg, you should say this: deadline <insert description> /by <insert " +
                        "time>");
            }
            return new CommandRunner(Command.DEADLINE, commandArguments);
        case EVENT:
            isInvalid =
                    (commandArugmentsLength < 2) || !(commandArguments[1].contains(" /from ")) ||
                        !(commandArguments[1].contains(" /to "));

            if (isInvalid) {
                throw new SnoopyException("Yo dawg, you should say this: event <insert description> /from <insert " +
                        "time> /to <insert time>");
            }
            return new CommandRunner(Command.EVENT, commandArguments);
        default:
            throw new InvalidCommandException("Yo dawg, I got no idea what ya saying!");
        }
    }
}