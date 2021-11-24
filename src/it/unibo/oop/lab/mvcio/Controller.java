package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import it.unibo.oop.lab.iogui.BadIOGUI;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    private File file;

    public Controller() {
        file = new File(System.getProperty("user.home")
            + System.getProperty("file.separator")
            + "output.txt");
    }

    /** 
     * @param newFile
     *      reference to the file to be set as current file
     */
    public void setCurrentFile(final File newFile) {
        file = newFile;
    }

    /**
     * 
     * @return
     *      returns current file
     */
    public File getCurrentFile() {
        return this.file;
    }

    /**
     * @return
     *      returns the path of the current File
     */
    public String getPath() {
        return this.file.getAbsolutePath();
    }

    /**
     * 
     * @param line
     *      string to be written to the file
     * @throws IOException
     */
    public void writeString(final String line) throws IOException {
        try (PrintStream ps = new PrintStream(file)) {
            ps.print(line);
        }
    }
}
