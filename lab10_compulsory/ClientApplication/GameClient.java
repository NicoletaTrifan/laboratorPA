import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            InetAddress ipAdress = InetAddress.getByName("localhost");
            Socket socket = new Socket(ipAdress, 8100);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println(dataInputStream.readUTF());
                String toSendMessage = scanner.nextLine();
                dataOutputStream.writeUTF(toSendMessage);
                if (toSendMessage.equals("exit")) {
                    socket.close();
                    System.out.println("[Client]Connection closed");
                    break;
                } else if (toSendMessage.equals("stop")) {
                    socket.close();
                    System.out.println("[Client] Server is stopped");
                    break;
                }

            }
            scanner.close();
            dataInputStream.close();
            dataOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
