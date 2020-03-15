package bowling.io.impl;

import bowling.io.OutputWrapper;

public class ConsoleOutputWrapper implements OutputWrapper {

    @Override
    public void displayNewLine() {
        displayNewLine("");
    }

    @Override
    public void displayFormatted(String format, Object... args) {
        System.out.printf(format, args);
    }

    @Override
    public void displayNewLine(String msg) {
        System.out.println(msg);
    }
}
