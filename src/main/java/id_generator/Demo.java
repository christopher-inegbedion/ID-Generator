package id_generator;

public class Demo {

    public static void main(String[] args) {
        String shop_name = "this is a test string that tests shop name";
        String execution_mode = "delivery";

        IDGenerator idGenerator = new IDGenerator(shop_name, execution_mode);
        System.out.println(idGenerator.constructID());
    }
}