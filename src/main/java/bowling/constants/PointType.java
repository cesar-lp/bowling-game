package bowling.constants;

public enum PointType {
    REGULAR(""), FOUL("F"), SPARE("/"), STRIKE("X");

    private String symbol;

    PointType(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
