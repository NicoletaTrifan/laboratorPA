import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private int numberOfElements;
    List<Token> tokenList;
    public Board(int size){
        tokenList=new ArrayList<Token>();
        this.numberOfElements=size;
    }
    public void boardElements(){
        for (int i=0; i<numberOfElements; i++){
            int randomNumberOfToken=(int)(Math.random()*numberOfElements);
            Token token=new Token(randomNumberOfToken);
            if (randomNumberOfToken != 0) {
                while (tokenList.contains(token)) {
                    randomNumberOfToken = (int) (Math.random() * numberOfElements);
                    token.setNumberOfToken(randomNumberOfToken);
                }
            }
            tokenList.add(token);
        }
        this.tokenList= Collections.synchronizedList(tokenList);
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    @Override
    public String toString() {
        return "Board{" +
                "numberOfElements=" + numberOfElements +
                ", tokenList=" + tokenList +
                '}';
    }
}
