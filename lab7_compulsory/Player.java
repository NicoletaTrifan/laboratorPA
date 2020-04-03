import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;

import static java.util.Collections.*;

public class Player implements Runnable{

    private String name;
    private Board board;
    private List<Token> synchronizedList;
    private List<Token> listOfTokens=new ArrayList<>();
    public Player(){
        Faker faker = new Faker();
        this.name=faker.name().fullName();
    }

    public void setBoard(Board board) {
        this.board = board;
        this.synchronizedList= board.getTokenList();
        this.synchronizedList=Collections.synchronizedList(synchronizedList);
    }
     public synchronized void claimElement(Token token){
            synchronizedList.remove(token);
            listOfTokens.add(token);
    }


    public synchronized void run() {
            int contor = synchronizedList.size();
            for (int i = 0; i < contor; i++) {
                Token token;
                token=synchronizedList.get(0);
                int number =token.getNumberOfToken();
                claimElement(token);
                System.out.println("Player  " + name + " claimed the " + number + " token ");
                contor--;
            }
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("Player : ");
        sb.append(this.getName());
        sb.append("  ");
        for (Token token: listOfTokens) {
            sb.append(token);
            sb.append(",");
        }
        return sb.toString();
    }
}
