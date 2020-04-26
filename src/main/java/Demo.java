import java.util.ArrayList;

public class Demo {

    public static void main(String[] args) {
        String shop_name = "this is my shop";
        String execution_mode = "delivery";

        IDGenerator idGenerator = new IDGenerator(60, shop_name.trim(), execution_mode, true);
        System.out.println(idGenerator.constructID());
    }
}
