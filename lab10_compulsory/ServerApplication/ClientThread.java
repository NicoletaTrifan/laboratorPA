

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    final Socket socket;

    public ClientThread(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            String recievedMessage;
            //String forReturnMessage;
            try {
                dataOutputStream.writeUTF("Enter your request : ");
                recievedMessage = dataInputStream.readUTF();
                if (recievedMessage.equals("stop")) {
                    this.socket.close();
                    System.out.println("Server stopped");
                    try {
                        // closing resources
                        this.dataInputStream.close();
                        this.dataOutputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.exit(0);
                }
                if (recievedMessage.equals("exit")) {
                    this.socket.close();
                    System.out.println("Client exited");
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.dataOutputStream.close();
            this.dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
