public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcons() {
        String statusIcons ="[E]";

        if (isDone) {
            statusIcons += "[X] ";
        } else {
            statusIcons += "[ ] ";
        }

        return statusIcons;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }
}
