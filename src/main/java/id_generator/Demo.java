package id_generator;

public class Demo {

    public static void main(String[] args) {
        String task_action = "deliver food";
        String execution_mode = "delivery";

        IDGenerator idGenerator = new IDGenerator(task_action, execution_mode);
        System.out.println(idGenerator.constructID());
    }
}