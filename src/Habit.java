public class Habit {
    private int id;
    private String name;
    private boolean completed;

    // Constructor
    public Habit(int id, String name) {
        this.id = id;
        this.name = name;
        this.completed = false; // By default, a new habit is NOT completed
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}