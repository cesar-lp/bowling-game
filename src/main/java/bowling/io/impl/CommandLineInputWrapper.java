package bowling.io.impl;

import bowling.io.InputWrapper;

import java.io.File;

public class CommandLineInputWrapper implements InputWrapper {

    private String fileName;

    public CommandLineInputWrapper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public File getFile() {
        return new File(fileName);
    }
}
