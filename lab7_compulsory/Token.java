public class Token {
    private int numberOfToken;
    public Token(int numberOfToken){
        this.numberOfToken=numberOfToken;
    }

    public Token() {
    }

    public void setNumberOfToken(int numberOfToken) {
        this.numberOfToken = numberOfToken;
    }

    public int getNumberOfToken() {
        return numberOfToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token = (Token) o;
        return numberOfToken == token.numberOfToken;
    }

    @Override
    public String toString() {
        return "Token{" +
                "numberOfToken=" + numberOfToken +
                '}';
    }
}

