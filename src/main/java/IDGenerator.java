/*
* class description: generates a task ID for a provider given their shop name and execution mode.
*
* how does it work: swaps each letter of the shop name and replaces it with a numerical representation.
*                   the same applies to the execution mode.
*                   each execution mode has a corresponding enum (ExecutionEnums.java) representation.
*                   concatenates the shop name and execution mode numbers along with the current nanotime and uses value as seed.
*                   generate a random character based on the returned random digit.
*
* version: 0.1
 * */

import java.util.Random;

public class IDGenerator {
    private final String shop_name;
    private final String execution_code;

    static int ID_LENGTH = 7;

    private final int bound;

    private final boolean isExecution;

    public IDGenerator(int bound, String shop_name, String execution_code, boolean isExecution) {
        if (bound == 0) throw new IllegalArgumentException("bound cannot be zero");
        if (shop_name.isEmpty()) throw new IllegalArgumentException("shop name cannot be empty");
        if (execution_code.isEmpty()) throw new IllegalArgumentException("execution code cannot be empty");

        if (isExecution) {
            if (!execution_code.equals("delivery")
                    && !execution_code.equals("meet_up_p")
                    && !execution_code.equals("meet_up_c"))
                throw new IllegalArgumentException("unsupported execution passed. supported codes: delivery, meet_up_c, meet_up_p");
        }

        this.shop_name = shop_name;
        this.execution_code = execution_code;

        this.bound = bound;
        this.isExecution = isExecution;

    }

    private long getShopNameInt() {
        HashFunc hashFunc = new HashFunc(shop_name);
        return hashFunc.convShopNameToLong();
    }

    private int getExecutionCodeInt() {
        HashFunc hashFunc = new HashFunc(execution_code, true);
        return hashFunc.convTaskActionToInt();
    }

    private long getSeedAtPosition() {
        StringBuilder stringBuilder = new StringBuilder();
        long shop_name_int = getShopNameInt();
        int execution_code = getExecutionCodeInt();
        stringBuilder.append(shop_name_int);
        stringBuilder.append(execution_code);
        stringBuilder.append(System.nanoTime()%1000);

        return Long.parseLong(stringBuilder.toString());
    }

    public int getNumberPosition(int position) {
        Random random = new Random();
        random.setSeed(getSeedAtPosition());
        int test = random.nextInt(bound);

        return test;
    }

    /*
    * description: create a new random ID based on the shop name and execution code passed
    * return: unique id generated
    *
    */
    public String constructID() {
        StringBuilder stringBuilder = new StringBuilder();
        int random_generated_number;

        for (int i = 0; i < ID_LENGTH; i++) {
            random_generated_number = getNumberPosition(i+1);

            if (random_generated_number >= 0 && random_generated_number <= 9) {
                stringBuilder.append(random_generated_number);

            } else if (random_generated_number >= 10 && random_generated_number <= 36) {
                stringBuilder.append((char)(random_generated_number-9 + 64));

            } else if (random_generated_number >= 37 && random_generated_number <= 62) {
                stringBuilder.append((char)(random_generated_number-35 + 96));

            } else {
                stringBuilder.append(random_generated_number);
            }
        }

        return stringBuilder.toString();
    }
}
