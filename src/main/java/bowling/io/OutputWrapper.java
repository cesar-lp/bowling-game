package bowling.io;

public interface OutputWrapper {

    void displayNewLine();

    void displayFormatted(String format, Object... args);

    void displayNewLine(String msg);
}
