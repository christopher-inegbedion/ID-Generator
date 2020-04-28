package id_generator;/*
* description: create numerical representation from shop name or execution mode
*
* version: 0.1
* author: Christopher Eromosele Inegbedion
* */

import org.apache.commons.codec.digest.DigestUtils;

public class HashFunc {
    private String text;
    private boolean isExecution = false;

    public HashFunc(String text) {

        if (text.isEmpty()) throw new IllegalArgumentException("text parameter cannot be empty");

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) throw new IllegalArgumentException("shop name parameter cannot have a digit");
        }
        this.text = text;
    }

    public HashFunc(String execution, boolean isExecution) {
        if (execution.isEmpty()) throw new IllegalArgumentException("execution parameter cannot be empty");

        if (!isExecution) throw new IllegalArgumentException("isExecution parameter has to be true if has hashing execution code.");

        if (!execution.equals("delivery")
                && !execution.equals("meet_up_p")
                && !execution.equals("meet_up_c"))
            throw new IllegalArgumentException("unsupported execution passed. supported codes: delivery, meet_up_c, meet_up_p");

        this.text = execution;
        this.isExecution = isExecution;
    }

    public ExecutionEnums getExecutionEnum() {
        switch (text) {
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
    public String hashShopName() {
        String hasedName = DigestUtils.sha1Hex(text);

        return hasedName.substring(0, ProjectSettings.ID_LENGTH);
    }

}
