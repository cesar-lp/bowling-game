package bowling.io;

public class TestOutputWrapper implements OutputWrapper {

    private StringBuilder sb = new StringBuilder();

    @Override
    public void displayNewLine() {
        displayNewLine("");
    }

    @Override
    public void displayFormatted(String format, Object... args) {
        sb.append(String.format(format, args));
    }

    @Override
    public void displayNewLine(String msg) {
        sb.append(msg).append("\n");
    }

    public String getResult() {
        return sb.toString();
    }

    public void clearResults() {
        sb = new StringBuilder();
    }
}
