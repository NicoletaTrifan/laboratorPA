public class Book implements Item {
    private String name;
    private int pageNumber;
    private int value;
    private int weight;

    public Book() {
    }

    public Book(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.pageNumber = (int) (weight * 100);
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }


    @Override
    public double profitFactor() {
        return getValue() / getWeight();
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", pageNumber=" + pageNumber +
                ", value=" + value +
                ", weight=" + weight +
                "(profit factor = " +
                this.profitFactor() + ")" +
                '}';
    }
}
