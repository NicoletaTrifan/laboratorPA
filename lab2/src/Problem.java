import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Problem.
 */
public class Problem {
    private Depot[] depots;
    private Client[] clients;

    /**
     * Instantiates a new Problem.
     */
    public Problem() {
    }

    /**
     * Get depots depot [ ].
     *
     * @return the depot [ ]
     */
    public Depot[] getDepots() {
        return depots;
    }

    /**
     * Sets depots.
     *
     * @param depots the depots
     */
    public void setDepots(Depot[] depots) {
        for (int i = 0; i < depots.length; i++) {
            for (int j = i + 1; j < depots.length; j++) {
                if (depots[i].equals(depots[j])) {
                    System.out.println("Problema nu permite sa aveti doua depozite cu acelasi nume!");
                    System.exit(1);
                }
            }
        }
        this.depots = depots;
    }

    /**
     * Gets clients.
     *
     * @return the clients
     */
    public ArrayList<Client> getClients() {
        ArrayList<Client> clientiReturnati = new ArrayList<Client>();
        for (int i = 0; i < clients.length; i++) {
            clientiReturnati.add(clients[i]);
        }
        return clientiReturnati;
    }

    /**
     * Sets clients.
     *
     * @param clients the clients
     */
    public void setClients(Client[] clients) {
        for (int i = 0; i < clients.length; i++) {
            for (int j = i + 1; j < clients.length; j++) {
                if (clients[i].equals(clients[j])) {
                    System.out.println("Problema nu permite sa aveti doi clienti cu acelasi nume!");
                    System.exit(1);
                }
            }
        }
        this.clients = clients;
    }

    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    public ArrayList<Vehicles> getVehicles() {
        ArrayList<Vehicles> vehicoleReturnate = new ArrayList<Vehicles>();
        Vehicles[] vehicole;
        for (int i = 0; i < depots.length; i++) {
            vehicole = depots[i].getVehicles();
            for (int j = 0; j < vehicole.length; j++) {
                vehicoleReturnate.add(vehicole[j]);
            }
        }
        return vehicoleReturnate;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Problem { ");
        sb.append("depots= '");
        for (Depot d : depots) {
            sb.append(d.getName());
            sb.append(" Vehicole = ");
            sb.append(Arrays.toString(d.getVehicles()));
            sb.append(" ");
        }

        sb.append("' clients='");
        for (Client c : clients) {
            sb.append(c.getName());
            sb.append("(");
            sb.append(c.getOrder());
            sb.append(")");
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }
}
