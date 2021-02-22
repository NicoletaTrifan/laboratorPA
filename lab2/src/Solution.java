import java.util.ArrayList;

/**
 * The type Solution.
 */
public class Solution {
    /**
     * The Vehicles.
     */
    Vehicles[] vehicles;
    /**
     * The Client.
     */
    Client[] client;
    /**
     * The Flag set.
     */
    int[] flagSet;
    /**
     * The Lista tururi.
     */
    ArrayList<Tour> listaTururi;
    //Tour tour;

    /**
     * Sets vehicles.
     *
     * @param vehicles the vehicles
     */
    public void setVehicles(Vehicles[] vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(Client[] client) {
        this.client = client;
        flagSet = new int[client.length];
        for (int i = 0; i < client.length; i++) {
            flagSet[i] = 0;
        }
    }

    /**
     * Instantiates a new Solution.
     */
    public Solution() {
        ArrayList<Tour> listaTururi = new ArrayList<>();
        vehicles = new Vehicles[100];
        client = new Client[100];
    //    tour = new Tour();
        flagSet = new int[100];
    }

    /**
     * Add tour.
     *
     * @param t the t
     */
    public void addTour(Tour t)
    {
        listaTururi.add(t);
    }

    /**
     * Greedy solution.
     */
    public void greedySolution() {
        listaTururi = new ArrayList<Tour>();
        for (Vehicles vehicle : vehicles) {
            Client[] clienti=new Client[5];
            Tour tour=new Tour();
            if (vehicle != null) {
                int order=0;
                int contorClienti = 0;
                tour.setVehicle(vehicle);
                clienti[contorClienti] = new Client();
                clienti[contorClienti].setName(vehicle.getDepotName());
                clienti[contorClienti].setOrder(order);
                contorClienti++;
                for (int j = 0; j < client.length; j++) {
                    if (client[j].getOrder() > order && flagSet[j] != 1) {
                        clienti[contorClienti] = new Client();
                        clienti[contorClienti].setName(client[j].getName());
                        clienti[contorClienti].setOrder(client[j].getOrder());
                        contorClienti++;
                        order = client[j].getOrder();
                        flagSet[j]=1;
                    }
                }
                clienti[contorClienti] = new Client();
                clienti[contorClienti].setName(vehicle.getDepotName());
                clienti[contorClienti].setOrder(5);
                tour.setClients(clienti);
                addTour(tour);
            }
        }
    }

    /**
     * Solutia.
     */
    public void solutia() {
        greedySolution();
        for (Tour t : listaTururi) {
            System.out.println(t.toString());
        }
    }
}
