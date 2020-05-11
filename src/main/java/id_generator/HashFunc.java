package id_generator;
/*
* description: create numerical representation from shop name or execution mode
*
* version: 0.3
* author: Christopher Inegbedion
* */

import enums.ExecutionEnums;
import org.apache.commons.codec.digest.DigestUtils;

public class HashFunc {
    private String task_action;
    private boolean isExecution = false;

    /*
    * description: called to hash a task action
    * */
    public HashFunc(String task_action) {

        if (task_action.isEmpty()) throw new IllegalArgumentException("text parameter cannot be empty");

        for (int i = 0; i < task_action.length(); i++) {
            if (Character.isDigit(task_action.charAt(i))) throw new IllegalArgumentException("shop name parameter cannot have a digit");
        }
        this.task_action = task_action;
    }

    /*
     * description: called to hash an execution code
     * */
    public HashFunc(String execution, boolean isExecution) {
        if (execution.isEmpty()) throw new IllegalArgumentException("execution parameter cannot be empty");

        if (!isExecution) throw new IllegalArgumentException("isExecution parameter has to be true if has hashing execution code.");

        if (!execution.equals("delivery")
                && !execution.equals("meet_up_p")
                && !execution.equals("meet_up_c"))
            throw new IllegalArgumentException("unsupported execution passed. supported codes: delivery, meet_up_c, meet_up_p");

        this.task_action = execution;
        this.isExecution = isExecution;
    }

    public ExecutionEnums getExecutionEnum() {
        switch (task_action) {
            case "delivery":
                return ExecutionEnums.DELIVERY;
            case "meet_up_p":
                return ExecutionEnums.MEET_UP_P;
            case "meet_up_c":
                return ExecutionEnums.MEET_UP_C;
            default:
                return null;
        }
    }

    /*
     * description: convert execution action to int
     * return: the corresponding task execution value
     * */
    public int convExexutionToInt() {
        if (isExecution) {
            return getExecutionEnum().getValue();
        } else {
            return 0;
        }
    }

    /*
    * description: perform SHA1 hash over the shop name and return the first 6 digits
    * return: hashed shop name
    * */
    public String hashTaskAction() {
        String hashedName = DigestUtils.sha1Hex(task_action);

        return hashedName.substring(0, ProjectSettings.ID_LENGTH);
    }

}
