/**
 * The type Drone.
 */
public class Drone extends Vehicles {
    private String name;

    /**
     * Instantiates a new Drone.
     *
     * @param name the name
     */
    public Drone(String name) {
        super(name);
    }
    public void setName(String name)
    {
        this.name=name;
    }
}
