/**
 * The type Car.
 */
public class Car extends Vehicles {
    private String name;

    /**
     * Instantiates a new Car.
     *
     * @param name the name
     */
    public Car(String name) {
        super(name);
    }
    public void setName(String name)
    {
        this.name=name;
    }
}
