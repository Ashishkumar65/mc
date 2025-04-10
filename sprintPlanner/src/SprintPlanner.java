import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SprintPlanner {
    private Map<String, Sprint> sprints;
    private static final int MAX_IN_PROGRESS_TASKS_PER_USER = 2;

    public SprintPlanner() {
        this.sprints = new HashMap<>();
    }

    public Sprint getSprints(String sprintId) {
        return sprints.get(sprintId);
    }

    public Sprint createSprint(String name){
        String sprintId = UUID.randomUUID().toString();
        Sprint sprint = new Sprint(sprintId,name);
        sprints.put(sprintId,sprint);
        return sprint;

    }

    public Task createTask(String sprintId, String title, TaskType type, String assignTO) throws IllegalAccessException {
        Sprint sprint = sprints.get(sprintId);
        if(sprint == null){
            throw new IllegalAccessException("Sprint not found");
        }
        String taskId = UUID.randomUUID().toString();
        Task task = new Task(taskId,title,type,assignTO,sprintId);
        if(!sprint.addTask(task)){
            throw new IllegalAccessException("Sprint has reached maximum task limit");
        }
        return task;

    }

    public boolean removetask(String sprintId, String taskId){
        Sprint sprint = sprints.get(sprintId);
        if(sprint == null){
            return false;
        }
        return sprint.removeTask(taskId);
    }
    public boolean updateTaskStatus(String sprintId, String taskId, TaskStatus newStatus){
        Sprint sprint = sprints.get(sprintId);
        if(sprint == null){
            return false;
        }
        Task task = sprint.getTasks().stream()
                .filter(t->t.getId().equals(taskId))
                .findFirst()
                .orElse(null);
        if(task == null){
            return false;
        }
        if(!isValidStatusTransititon(task.getStatus(),newStatus)){
            return false;
        }
        if(newStatus == TaskStatus.IN_PROGRESS){
            int inProgressCount = sprint.getInProgressTasksCountForUser(task.getAssignedTo());
            if (inProgressCount >= MAX_IN_PROGRESS_TASKS_PER_USER) {
                return false;
            }
        }
        task.setStatus(newStatus);
        return true;
    }
    public List<Task> getUserTasks(String sprintId, String userId) {
        Sprint sprint = sprints.get(sprintId);
        if (sprint == null) {
            return List.of();
        }
        return sprint.getTasksByUser(userId);
    }

    public List<Task> getDelayedTasks(String sprintId) {
        Sprint sprint = sprints.get(sprintId);
        if (sprint == null) {
            return List.of();
        }
        return sprint.getDelayedTasks();
    }
    private boolean isValidStatusTransititon(TaskStatus currentStatus, TaskStatus newStatus){
        return switch(currentStatus){
            case TODO -> newStatus == TaskStatus.IN_PROGRESS;
            case IN_PROGRESS -> newStatus == TaskStatus.TODO || newStatus == TaskStatus.DONE;
            case DONE -> false;
        };
    }


}
