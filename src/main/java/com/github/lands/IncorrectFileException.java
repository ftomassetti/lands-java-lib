package com.github.lands;

import java.io.File;

/**
 * A file could not be loaded because its format was not correct.
 */
public class IncorrectFileException extends Exception {

    private final File incorrectFile;
    private final String errorDescription;

    public IncorrectFileException(File incorrectFile, String errorDescription) {
        super("Incorrect file: "+incorrectFile+". "+errorDescription);
        if (incorrectFile == null) throw new NullPointerException();
        if (errorDescription == null) throw new NullPointerException();
        this.incorrectFile = incorrectFile;
        this.errorDescription = errorDescription;
    }

    public IncorrectFileException(File incorrectFile, String errorDescription, Throwable cause) {
        super("Incorrect file: "+incorrectFile+". "+errorDescription, cause);
        if (incorrectFile == null) throw new NullPointerException();
        if (errorDescription == null) throw new NullPointerException();
        this.incorrectFile = incorrectFile;
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return "File "+incorrectFile+" is incorrect because "+errorDescription;
    }
}
