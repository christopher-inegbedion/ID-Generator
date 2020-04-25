public class Demo {

    public static void main(String[] args) {
        HashFunc hashFunc = new HashFunc("delivery", true);
        System.out.println(hashFunc.convTaskActionToInt());
    }
}
