package net.ukr.dandy1988.recycleview;

public class SwimEvent {
    private final String date;
    private final String description;
    private final int priority;

    public SwimEvent(String date, String description, int priority) {
        this.date = date;
        this.description = description;
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
