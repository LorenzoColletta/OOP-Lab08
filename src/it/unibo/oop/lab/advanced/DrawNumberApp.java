package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

//    private static final int MIN = 0;
//    private static final int MAX = 100;
//    private static final int ATTEMPTS = 10;
//    private final Configuration config;
    private final DrawNumber model;
//    private final DrawNumberView view;
    private final List<DrawNumberView> views;

    /**
     * @param fileConfig
     *      file to retrieve configuration from
     */
    public DrawNumberApp(final String fileConfig, final DrawNumberView... args) {
        this.views = Arrays.asList(Arrays.copyOf(args, args.length));
        for (DrawNumberView i : views) {
            i.setObserver(this);
            i.start();
        }

        var min = 0;
        var max = 0;
        var attempts = 0;
        try (BufferedReader configReader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileConfig)))) {
            for (String line = configReader.readLine(); line != null; line = configReader.readLine()) {
                if (line.contains("max")) {
                    max = Integer.parseInt(line.replaceFirst("maximum: ", ""));
                } else if (line.contains("attempts")) {
                    attempts = Integer.parseInt(line.replaceFirst("attempts: ", ""));
                } else if (line.contains("min")) {
                    min = Integer.parseInt(line.replaceFirst("minimum: ", ""));
                } else {
                    this.displayError("Wrong file format.");
                }
            }
        } catch (IOException e1) {
            this.displayError("IO Error: " + e1.getMessage());
        }
        this.model = new DrawNumberImpl(min, max, attempts);

    }

    private void displayError(final String error) {
        for (final DrawNumberView i : views) {
            i.displayError(error);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e1) {
            for (final DrawNumberView view: views) {
                view.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        try {
            new DrawNumberApp("config.yml", new DrawNumberViewImpl(), new DrawNumberViewSpecific(new PrintStream(new FileOutputStream(new File("File.log")))), new DrawNumberViewSpecific(System.out));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
