import java.util.ArrayList;
import java.util.List;

public class Sprint {
    private final String id;
    private final String name;
    private List<Task> tasks;
    private static final int Max_Tasks = 20;

    public Sprint(String id, String name){
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean addTask(Task task){
        if(tasks.size() > Max_Tasks){
            return false;
        }
        return tasks.add(task);
    }
    public boolean removeTask(String taskId){
        return tasks.removeIf(task->task.getId().equals(taskId));
    }

    public List<Task> getTasksByUser(String userId){
        return tasks.stream()
                .filter(task->task.getAssignedTo().equals(userId)).toList();
    }

    public List<Task> getDelayedTasks(){
        return tasks.stream()
                .filter(task->task.getStatus() != TaskStatus.DONE)
                .toList();
    }
    public int getInProgressTasksCountForUser(String userId) {
        return (int) tasks.stream()
                .filter(task -> task.getAssignedTo().equals(userId)
                        && task.getStatus() == TaskStatus.IN_PROGRESS)
                .count();
    }
    @Override
    public String toString(){
        return "Sprint{" +
                "id='" + id + '\''+
                ",name='" + name + '\'' +
                ",tasks='" + tasks + '\'' + '}';
    }

}
