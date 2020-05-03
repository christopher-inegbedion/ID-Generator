package id_generator;/*
* class description: generates a task ID for a provider given their shop name and execution mode.
*
* how does it work: generate a hash value for the task name using SHA-1 hex hash and select first 10 digits
*                   return concatenated hash value and execution code e.g (ais13jf91i-2) -> constructID
*
* version: 0.1
* author: Christopher Eromosele Inegbedion
 * */

public class IDGenerator {
    private String task_action;
    private String execution_code;

    public IDGenerator(String task_action, String execution_code) {
        if (task_action.isEmpty()) throw new IllegalArgumentException("shop name cannot be empty");
        if (execution_code.isEmpty()) throw new IllegalArgumentException("execution code cannot be empty");

        this.task_action = task_action.trim();
        this.execution_code = execution_code;
    }

    private String getTaskActionHash() {
        HashFunc hashFunc = new HashFunc(task_action);
        return hashFunc.hashTaskAction();
    }

    private int getExecutionCodeInt() {
        HashFunc hashFunc = new HashFunc(execution_code, true);
        return hashFunc.convExexutionToInt();
    }

    /*
    * description: create a new ID based on the hashed shop name and execution code passed
    * return: unique id generated
    *
    */
    public String constructID() {
        String hashed_task_action = getTaskActionHash();
        int hashed_execution_code = getExecutionCodeInt();

        return hashed_task_action + "-" + hashed_execution_code;
    }
}
