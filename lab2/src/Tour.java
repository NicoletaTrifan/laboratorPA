import java.util.Arrays;

/**
 * The type Tour.
 */
public class Tour {
    private Vehicles vehicle;
    private Client[] clients;

    /**
     * Instantiates a new Tour.
     */
    public Tour() {
        clients = new Client[5];

    }

    /**
     * Get clients client [ ].
     *
     * @return the client [ ]
     */
    public Client[] getClients() {
        return clients;
    }

    /**
     * Sets clients.
     *
     * @param clients the clients
     */
    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    /**
     * Gets vehicle.
     *
     * @return the vehicle
     */
    public Vehicles getVehicle() {
        return vehicle;
    }

    /**
     * Sets vehicle.
     *
     * @param vehicle the vehicle
     */
    public void setVehicle(Vehicles vehicle) {
        this.vehicle = vehicle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle ");
        sb.append(vehicle);
        sb.append(" ");
        for (int i = 0; i < clients.length ; i++) {
            if (clients[i] != null) {
                sb.append(clients[i]);
                if (i==clients.length-1 || clients[i+1]==null)
                {
                    sb.append(" ");
                }
                else sb.append("->");
            }
        }
        return sb.toString();
    }
}

