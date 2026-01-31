public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcons() {
        String statusIcons ="[T]";

        if (isDone) {
            statusIcons += "[X] ";
        } else {
            statusIcons += "[ ] ";
        }

        return statusIcons;
    }
}
