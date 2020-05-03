import id_generator.HashFunc;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashFunctionTest {


    /*
    * id_generator.HashFunc param tests
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
        String shop_name_value = hashFunc.hashTaskAction();

        assertNotEquals(shop_name_value, "");
    }

    @Test
    public void testNonExecutionCodeCannotBeConvertedToExecutionCode() {
        HashFunc hashFunc = new HashFunc("m");
        long char_to_long = hashFunc.convExexutionToInt();

        assertEquals(char_to_long, 0);
    }

    /*
    * execution code tests
    * */
    @Test
    public void testTaskExecutionReturnsCorrectValue() {
        HashFunc hashFunc = new HashFunc("delivery", true);

        int task_execution = hashFunc.convExexutionToInt();
        assertNotEquals(task_execution, 0);
    }


}
