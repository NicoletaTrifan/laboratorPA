public interface Item {
    String getName();

    int getValue();

    int getWeight();

    default double profitFactor() {
        return getValue() / getWeight();
    }
}
