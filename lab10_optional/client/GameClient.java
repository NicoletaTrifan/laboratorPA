import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            String receivedMessage;
            InetAddress ipAdress = InetAddress.getByName("localhost");
            Socket socket = new Socket(ipAdress, 8100);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            int playerId = dataInputStream.readInt();
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
                else if (toSendMessage.equals("createGame")) {
                    System.out.println("Enter the game id: ");
                    int id = scanner.nextInt();
                    dataOutputStream.writeInt(id);
                    boolean validIdStatus = dataInputStream.readBoolean();
                    System.out.println(validIdStatus);
                    if (validIdStatus) {
                        System.out.println("Game created sucessfully!");
                        System.out.println("Waiting for players!");

                        boolean canStartGame = dataInputStream.readBoolean();
                        while (!canStartGame) {
                            canStartGame = dataInputStream.readBoolean();
                        }
                        System.out.println("Player ID: " + playerId);
                    }
                    else {
                        System.out.println("A game with id:" + id + " already exists!");
                        System.out.println("Please use `join game` option or create a game with another id!");
                    }

                }
                else if (toSendMessage.equals("joinGame")) {
                    receivedMessage = dataInputStream.readUTF();
                    System.out.println(receivedMessage);
                    System.out.println("Enter the id of game you want to join!");

                    int id = scanner.nextInt();
                    dataOutputStream.writeInt(id);
                    boolean joinStatus = dataInputStream.readBoolean();
                    if (joinStatus) {
                        System.out.println("The game is starting in 5 seconds... ");
                        System.out.println("PLAYER ID: " + playerId);

                    }
                    else {
                        System.out.println("The room is full or does not exists!");
                    }
                }
                else if (toSendMessage.equals("submitMove")) {
                    receivedMessage = dataInputStream.readUTF();
                    System.out.println(receivedMessage);
                    while (true) {
                         System.out.println("Enter again your game id:");
                         int id=scanner.nextInt();
                         System.out.println("Enter your move: ");
                         System.out.print("x = ");
                         int x = scanner.nextInt();
                         System.out.print("y = ");
                         int y = scanner.nextInt();
                         dataOutputStream.writeInt(x);
                         dataOutputStream.writeInt(y);
                         dataOutputStream.writeInt(id);

                     }
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
