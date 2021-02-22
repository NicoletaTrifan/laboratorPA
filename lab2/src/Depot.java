import java.io.*;
import java.util.*;

/**
 * The type Depot.
 */
public class Depot {
    private String name;
    private Vehicles[] vehicles;

    /**
     * Instantiates a new Depot.
     *
     * @param name the name
     */
    public Depot(String name) {
        this.name = name;
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
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets vehicles.
     *
     * @param vehicles the vehicles
     */
    public void setVehicles(Vehicles... vehicles) {
        this.vehicles = vehicles;
        for (int i = 0; i < vehicles.length; i++) {
            for (int j = i + 1; j < vehicles.length; j++) {
                if (vehicles[i].equals(vehicles[j])) {
                    System.out.println("Nu puteti avea duplicate de vehicule intr-un depozit");
                    System.exit(1);
                }
            }
        }
        for (Vehicles v : vehicles) {
            if (v.getDepot() == null) {
                v.setDepot(this);
            } else {
                System.out.println("Vehicolul " + v.getName() + " are deja depozit");
                System.exit(1);
            }
        }
    }

    /**
     * Get vehicles vehicles [ ].
     *
     * @return the vehicles [ ]
     */
    public Vehicles[] getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Depot { ");
        sb.append("name= '");
        sb.append(this.getName());
        sb.append("'");
        sb.append(" vehicles= ");
        for (Vehicles v : vehicles) {
            sb.append(v.getName());
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Depot)) {
            return false;
        }
        Depot other = (Depot) obj;
        return name.equals(other.name);
    }
}
