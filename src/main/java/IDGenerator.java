/*
* class description: generates a task ID for a provider given their shop name and execution mode.
*
* how does it work: generate a hash value for the shop using SHA-1 hex hash and select first 10 digits
*                   return concatenated hash value and execution code e.g (ais13jf91i-2) -> constructID
*
* version: 0.1
* author: Christopher Eromosele Inegbedion
 * */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IDGenerator {
    private String shop_name;
    private String execution_code;

    public IDGenerator(String shop_name, String execution_code) {
        if (shop_name.isEmpty()) throw new IllegalArgumentException("shop name cannot be empty");
        if (execution_code.isEmpty()) throw new IllegalArgumentException("execution code cannot be empty");

        this.shop_name = shop_name.trim();
        this.execution_code = execution_code;
    }

    private String getShopNameHash() {
        HashFunc hashFunc = new HashFunc(shop_name);
        return hashFunc.hashShopName();
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
        String hashed_shop_name = getShopNameHash();
        int hashed_execution_code = getExecutionCodeInt();

        return hashed_shop_name + "-" + hashed_execution_code;
    }
}
