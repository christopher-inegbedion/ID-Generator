public enum  ExecutionEnums {
    DELIVERY(1),
    MEET_UP_P(2),
    MEET_UP_C(3);

    private int value;

    ExecutionEnums(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

}
