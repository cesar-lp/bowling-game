package bowling.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class TestInputWrapper implements InputWrapper {

    private String fileName;

    public TestInputWrapper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public File getFile() {
        URL resource = getClass().getResource(fileName);
        return FileUtils.toFile(resource);
    }
}
