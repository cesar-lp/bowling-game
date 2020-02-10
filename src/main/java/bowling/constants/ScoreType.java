package bowling.constants;

public enum ScoreType {
    REGULAR(""), SPARE("/"), STRIKE("X");

    private String symbol;

    ScoreType(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
