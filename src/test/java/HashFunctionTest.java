import org.junit.Test;

import static org.junit.Assert.*;

public class HashFunctionTest {


    /*
    * HashFunc param tests
    * */
    @Test(expected = IllegalArgumentException.class)
    public void testHashFuncShopNameParamCannotBeEmpty() {
        HashFunc hashFunc = new HashFunc("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHashFuncHasToBeString() {
        HashFunc hashFunc = new HashFunc("12345");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHasFuncTaskParamCannotBeEmpty() {
        HashFunc hashFunc = new HashFunc("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsExecutionArgCannotBeFalse() {
        HashFunc hashFunc = new HashFunc("e", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCorrectExecutionPassedAsArgumentCode() {
        HashFunc hashFunc = new HashFunc("wrong", true);
    }


    /*
    * shop name tests
    * */
    @Test
    public void testShopNameReturnsCorrectValue() {
        HashFunc hashFunc = new HashFunc("move");

        int shop_name_value = hashFunc.convShopNameToInt();

        assertNotEquals("shop name:", shop_name_value, 0);
    }

    @Test
    public void testCharToIntReturnValueNotEqualsTo0() {
        HashFunc hashFunc = new HashFunc("abcdefghijklmnopqrstuvwxyz");
        int char_to_int = hashFunc.shopNameToInt();

        assertNotEquals(char_to_int, 0);
    }

    /*
    * execution code tests
    * */
    @Test
    public void testTaskExecutionReturnsCorrectValue() {
        HashFunc hashFunc = new HashFunc("delivery", true);

        int task_execution = hashFunc.convTaskActionToInt();
        assertNotEquals("task execution", task_execution, 0);
    }


}
