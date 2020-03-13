public class Weapon implements Item {
    public enum WeaponType {
        SWORD;
    }

    private WeaponType type;
    private int weight;
    private int value;

    public Weapon() {
    }

    public Weapon(WeaponType type, int weight, int value) {
        this.type = type;
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public double profitFactor() {
        return getValue() / getWeight();
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "type=" + type +
                ", weight=" + weight +
                ", value=" + value +
                "(profit factor = " +
                this.profitFactor() + ")" +
                '}';
    }
}
