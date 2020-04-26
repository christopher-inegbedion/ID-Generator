import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.fail;

public class IDGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBoundCannotNot0() {
        IDGenerator idGenerator = new IDGenerator(0, "1", "1", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShopNameCannotEmpty() {
        IDGenerator idGenerator = new IDGenerator(1, "", "1", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecutionCannotEmpty() {
        IDGenerator idGenerator = new IDGenerator(1, "1", "", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForCorrectExecutionCodeIfIsExecutionTrue() {
        IDGenerator idGenerator = new IDGenerator(1, "1", "fs", true);
    }

    /*
    * Test randomness
    * */
    @Test
    public void testRandomness() {
        int number_of_tests = 100;
        int text_length = 100;

        int number_of_runs = 1;

        ArrayList<String> unique_ids = new ArrayList<>();
        ArrayList<String> tested_names = new ArrayList<>();

        for (int l = 0; l < number_of_runs; l++) {
            System.out.println("repetition " + (l+1));
            //Generate random texts of different lengths
            for (int i = 0; i < number_of_tests; i++) {
                int leftLimit = 97; // letter 'a'
                int rightLimit = 122; // letter 'z'
                Random random = new Random();
                StringBuilder buffer = new StringBuilder(text_length);

                for (int k = 0; k < text_length; k++) {
                    int randomLimitedInt = leftLimit + (int)
                            (random.nextFloat() * (rightLimit - leftLimit + 1));
                    buffer.append((char) randomLimitedInt);
                }

                tested_names.add(buffer.toString());

            }

            for (int i = 0; i < tested_names.size(); i++) {
                IDGenerator idGenerator = new IDGenerator(63, tested_names.get(i), "meet_up_c", true);

                if (unique_ids.contains(idGenerator.constructID())) {
                    fail("duplicate found!");
                } else {
                    unique_ids.add(idGenerator.constructID());
                }
            }

            System.out.println(">passed!");
        }
    }
}
