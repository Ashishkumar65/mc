public class Task {
    private final String id;
    private final String title;
    private final TaskType type;
    private TaskStatus status;
    private final String assignedTo;
    private final String sprintId;

    public Task(String id, String title, TaskType type,  String assignedTo, String sprintId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.status = TaskStatus.TODO;
        this.assignedTo = assignedTo;
        this.sprintId = sprintId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskType getType() {
        return type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getSprintId() {
        return sprintId;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", assignedTo='" + assignedTo + '\'' +
                ", sprintId='" + sprintId + '\'' +
                '}';
    }
}
