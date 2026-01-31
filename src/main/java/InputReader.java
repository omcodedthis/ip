public class InputReader {
    public static CommandRunner readInput(String input) throws Exception {
        String[] commandArguments = input.trim().split(" ", 2);
        String command = commandArguments[0].toLowerCase();

        switch (command) {
        case "bye":
            return new CommandRunner(Command.BYE, commandArguments);

        case "list":
            return new CommandRunner(Command.LIST, commandArguments);

        case "mark":
            return new CommandRunner(Command.MARK, commandArguments);

        case "unmark":
            return new CommandRunner(Command.UNMARK, commandArguments);

        case "todo":
            return new CommandRunner(Command.TODO, commandArguments);

        case "deadline":
            return new CommandRunner(Command.DEADLINE, commandArguments);

        case "event":
            return new CommandRunner(Command.EVENT, commandArguments);

        default:
            throw new Exception();
        }

    }
}
