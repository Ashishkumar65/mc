public class Main {
    public static void main(String[] args)  {
        SprintPlanner planner = new SprintPlanner();
        // Create a sprint
        try {
            Sprint sprint = planner.createSprint("Sprint 1");
            System.out.println("Created sprint: " + sprint.getName());

            // Create tasks
//            for(int i =0; i< 20;i++){
//                Task task1 = planner.createTask(sprint.getId(), "Implement login", TaskType.FEATURE, "user1");
//            }
            Task task2 = planner.createTask(sprint.getId(), "Fix navigation bug", TaskType.BUG, "user1");
            Task task3 = planner.createTask(sprint.getId(), "Add user profile", TaskType.STORY, "user2");
            Task task1 = planner.createTask(sprint.getId(), "Implement login", TaskType.FEATURE, "user1");

            System.out.println("\nCreated tasks:");
            System.out.println(task1);
            System.out.println(task2);
            System.out.println(task3);

            // Update task status
            planner.updateTaskStatus(sprint.getId(), task1.getId(), TaskStatus.IN_PROGRESS);
            planner.updateTaskStatus(sprint.getId(), task2.getId(), TaskStatus.IN_PROGRESS);
            planner.updateTaskStatus(sprint.getId(), task3.getId(), TaskStatus.IN_PROGRESS);

            System.out.println("\nTasks after status updates:");
            System.out.println(task1);
            System.out.println(task2);
            System.out.println(task3);

            // Get tasks for a user
            System.out.println("\nTasks assigned to user1:");
            planner.getUserTasks(sprint.getId(), "user1").forEach(System.out::println);

            // Get delayed tasks
            System.out.println("\nDelayed tasks:");
            planner.getDelayedTasks(sprint.getId()).forEach(System.out::println);

            // Try to update task status to DONE
            planner.updateTaskStatus(sprint.getId(), task1.getId(), TaskStatus.DONE);
            System.out.println("\nTask1 after marking as done:");
            System.out.println(task1);

        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
