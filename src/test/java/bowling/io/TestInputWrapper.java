package bowling.io;

public class TestInputWrapper implements InputWrapper {

    private String fileName;

    public TestInputWrapper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String readLine() {
        return fileName;
    }
}
