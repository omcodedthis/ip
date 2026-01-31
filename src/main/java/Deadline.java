public class Deadline extends Task {

    protected String doBy;

    public Deadline(String description, String doBy) {
        super(description);
        this.doBy = doBy;
    }

    @Override
    public String getStatusIcons() {
        String statusIcons ="[D]";

        if (isDone) {
            statusIcons += "[X] ";
        } else {
            statusIcons += "[ ] ";
        }

        return statusIcons;
    }

    public String getDoBy() {
        return doBy;
    }
}
