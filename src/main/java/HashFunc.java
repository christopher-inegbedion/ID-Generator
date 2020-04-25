public class HashFunc {
    private String text;
    private boolean isExecution = false;
    private long conv;

    public HashFunc(String text) {
        if (text.isEmpty()) throw new IllegalArgumentException("text parameter cannot be empty");

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) throw new IllegalArgumentException("text parameter cannot be empty");
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

    /*
    * description: convert shop name to int
    * return: a value between 1 and 27
    * */
    public int convShopNameToInt() {
        return shopNameToInt();
    }

    /*
     * description: convert task action to int
     * return: the corresponding task execution value
     * */
    public int convTaskActionToInt() {
        if (isExecution) {
            return getExecutionEnum().getValue();
        } else {
            return 0;
        }
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

    public int shopNameToInt() {
        int final_value = 0;
        char char_value;

        for (int i = 0; i < text.length(); i++) {
            char_value = text.charAt(i);

            if (char_value >= 65 && char_value <= 90) {
                final_value += (char_value-65+1)*(i+1);
            } else if (char_value >= 97 && char_value <= 122) {
                final_value += (char_value-97+1)*(i+1);
            }
        }

        final_value =  final_value % 10000;

        return final_value;
    }

}
