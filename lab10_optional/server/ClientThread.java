import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

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
    public synchronized void run() {
        int threadId = GameServer.getThreadId(this);
        int playerId = GameController.playerId++;
        int id;
        try {
            dataOutputStream.writeInt(playerId);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            String recievedMessage;
            String returnMessage;//String forReturnMessage;
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
                if (recievedMessage.equals("createGame")) {
                    id = dataInputStream.readInt();
                    System.out.println("ID received " + id);
                    boolean validIdStatus = GameController.isValidId(id);
                    dataOutputStream.writeBoolean(validIdStatus);
                    if (validIdStatus) {
                        // am un valid id, deci creez jocul
                        GameController.addGame(id);
                        GameController.addGameId(id, threadId);
                    }
                    boolean canStartStatus = GameController.areWeReadyToPlay(id);
                    dataOutputStream.writeBoolean(canStartStatus);
                    while (!canStartStatus) {
                        canStartStatus = GameController.areWeReadyToPlay(id);
                        dataOutputStream.writeBoolean(canStartStatus);
                    }
                    GameController.currentGame = GameController.getGameById(id);
                    while (true) {
                        int order = dataInputStream.readInt();
                        int x = dataInputStream.readInt();
                        int y = dataInputStream.readInt();
                        boolean moveValidStatus = true;
                        if (GameController.isValidMove(1, x, y)) {
                            GameController.submitPlayer1Move(id, x, y);
                            dataOutputStream.writeBoolean(moveValidStatus);
                        } else {
                            dataOutputStream.writeBoolean(!moveValidStatus);
                            System.out.println("Invalid move!");
                        }
                    }
                }
                if (recievedMessage.equals("joinGame")) {
                    dataOutputStream.writeUTF("Joined Game!");
                    id = dataInputStream.readInt();
                    boolean joinStatus = GameController.isValidForJoin(id);
                    dataOutputStream.writeBoolean(joinStatus);
                    if (joinStatus) {
                        GameController.markAsJoinedForBeggingToPlay(id, threadId);
                    }
                    GameController.currentGame = GameController.getGameById(id);
                    while (true) {
                        int order = dataInputStream.readInt();
                        int x = dataInputStream.readInt();
                        int y = dataInputStream.readInt();
                        boolean moveValidStatus = true;
                        if (GameController.isValidMove(2, x, y)) {
                            GameController.submitPlayer2Move(id, x, y);
                            dataOutputStream.writeBoolean(moveValidStatus);
                        } else {
                            System.out.println("Invalid move!");
                            dataOutputStream.writeBoolean(!moveValidStatus);
                        }
                    }
                }
                if (recievedMessage.equals("submitMove")) {
                    dataOutputStream.writeUTF("You made your move");
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
