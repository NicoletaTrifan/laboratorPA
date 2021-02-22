import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Solution s=new Solution();
        Problem pb = new Problem();
        Depot d1 = new Depot("Depot 1");
        Depot d2 = new Depot("Depot 2");
        Car v1 = new Car("Vehicle1");
        Truck v2 = new Truck("Vehicle2");
        Drone v3 = new Drone("Vehicle3");
        d1.setVehicles(v1, v2);
        d2.setVehicles(v3);
        Client c1 = new Client("C1", 1);
        Client c2 = new Client("C2", 1);
        Client c3 = new Client("C3", 2);
        Client c4 = new Client("C4", 2);
        Client c5 = new Client("C5", 3);
        Depot[] depots = new Depot[2];
        depots[0] = d1;
        depots[1] = d2;
        pb.setDepots(depots);
        Client[] clients = new Client[5];
        clients[0] = c1;
        clients[1] = c2;
        clients[2] = c3;
        clients[3] = c4;
        clients[4] = c5;
        pb.setClients(clients);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(v1 + "\n" + v2 + "\n" + v3);
        System.out.println(pb);
        s.setClient(clients);
        Vehicles[] vehicles=new Vehicles[3];
        vehicles[0]=v1;
        vehicles[1]=v2;
        vehicles[2]=v3;
        s.setVehicles(vehicles);
        s.solutia();
    }
}
