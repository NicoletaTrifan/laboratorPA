import java.util.Objects;

/**
 * The type Vehicles.
 */
public abstract class Vehicles {
    private String name;
    private Depot depot;

    /**
     * Instantiates a new Vehicles.
     *
     * @param name the name
     */
    public Vehicles(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new Vehicles.
     */
    protected Vehicles() {
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    abstract void setName(String name);

    /**
     * Gets depot.
     *
     * @return the depot
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * Gets depot name.
     *
     * @return the depot name
     */
    public String getDepotName() {
        return depot.getName();
    }

    /**
     * Sets depot.
     *
     * @param depot the depot
     */
    protected void setDepot(Depot depot) {
        this.depot = depot;
    }

    @Override
    public String toString() {
        return "Vehicles{" +
                "name='" + name + '\'' +
                ", depot=" + depot +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vehicles)) {
            return false;
        }
        Vehicles other = (Vehicles) obj;
        return name.equals(other.name);
    }
}
