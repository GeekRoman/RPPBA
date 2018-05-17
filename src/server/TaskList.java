package server;

public class TaskList {
    public String TaskListId;
    public String Name;
    public String Status;
    public TaskList(){}

    public TaskList (String TaskListId,String Name,String Status){
        this.TaskListId = TaskListId;
        this.Name = Name;
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public String getName() {
        return Name;
    }

    public String getTaskListId() {
        return TaskListId;
    }
}
