public class Food implements Item {
    private String name;
    private int weight;

    public Food() {
    }

    public Food(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getValue() {
        return weight * 2;
    }

    @Override
    public double profitFactor() {
        return getValue() / getWeight();
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + this.getValue() +
                "(profit factor = " +
                this.profitFactor() + ")" +
                '}';
    }
}
