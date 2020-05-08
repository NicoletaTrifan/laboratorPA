import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 8100;
    public static final Thread[] threads = new Thread[128];
    public static int idx = 0;

    public static int getThreadId(Thread thread) {
        for (int i = 0; i < idx; ++i) {
            if (thread == threads[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for client");
                socket = serverSocket.accept();
                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("Accepted client");
                Thread threadClient = new ClientThread(socket, dis, dos);
                threads[idx++] = threadClient;
                System.out.println("[SERVER] Thread id: " + idx);
                threadClient.start();

            }
        } catch (Exception e) {
            socket.close();
            e.printStackTrace();
        }
    }
}
