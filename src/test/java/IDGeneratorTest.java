import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.fail;

public class IDGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testShopNameCannotEmpty() {
        IDGenerator idGenerator = new IDGenerator("", "1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecutionCannotEmpty() {
        IDGenerator idGenerator = new IDGenerator( "1", "");
    }

    /*
    * Test randomness
    * */
    @Test
    public void testRandomness() {

        ArrayList<String> unique_ids = new ArrayList<>();
        ArrayList<String> unique_characters = new ArrayList<>();

        //loop test x amount of times
        for (int l = 0; l < ProjectSettings.Test.number_of_runs; l++) {
            HashMap<String, String> unique_tested_names = new HashMap<>();
            ArrayList<String> tested_names = new ArrayList<>();

            System.out.println("repetition: " + (l+1));

            //Generate random texts of different lengths
            for (int i = 0; i < ProjectSettings.Test.number_of_tests; i++) {
                int leftLimit = 97;
                int rightLimit = 122;
                Random random = new Random();
                StringBuilder buffer = new StringBuilder(ProjectSettings.Test.text_length);

                for (int k = 0; k < ProjectSettings.Test.text_length; k++) {
                    int randomLimitedInt = leftLimit + (int)
                            (random.nextFloat() * (rightLimit - leftLimit + 1));
                    buffer.append((char) randomLimitedInt);
                }

                //confirm generates string is unique
                if (!unique_characters.contains(buffer.toString())) {
                    unique_characters.add(buffer.toString());
                    tested_names.add(buffer.toString());
                }

            }

            //check duplicate is not in list
            for (String tested_name : tested_names) {
                IDGenerator idGenerator = new IDGenerator(tested_name, "delivery");
                unique_tested_names.put(tested_name, idGenerator.constructID());

                if (unique_ids.contains(idGenerator.constructID())) {
//                    for (Map.Entry entry : unique_tested_names.entrySet()) {
//                        System.out.println(entry.getKey() + "->" + entry.getValue());
//                    }
                    fail("duplicate found! ->" + idGenerator.constructID());
                } else {
                    unique_ids.add(idGenerator.constructID());
                }
            }

            System.out.println(">passed!");
        }
    }
}
