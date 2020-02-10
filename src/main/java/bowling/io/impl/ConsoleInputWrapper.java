package bowling.io.impl;

import bowling.io.InputWrapper;

import java.util.Scanner;

public class ConsoleInputWrapper implements InputWrapper {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
