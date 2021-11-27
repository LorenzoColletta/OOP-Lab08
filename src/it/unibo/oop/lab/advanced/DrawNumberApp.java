package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

//    private static final int MIN = 0;
//    private static final int MAX = 100;
//    private static final int ATTEMPTS = 10;
//    private final Configuration config;
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * @param fileConfig
     *      file to retrieve configuration from
     */
    public DrawNumberApp(final String fileConfig) {
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
        var min = 0;
        var max = 0;
        var attempts = 0;
        try (BufferedReader configReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileConfig)))) {
            for (String line = configReader.readLine(); line != null; line = configReader.readLine()) {
                if (line.contains("max")) {
                    max = Integer.parseInt(line.replaceFirst("max: ", ""));
                } else if (line.contains("attempts")) {
                    attempts = Integer.parseInt(line.replaceFirst("attempts: ", ""));
                } else if (line.contains("min")) {
                    min = Integer.parseInt(line.replaceFirst("min: ", ""));
                } else {
                    this.view.displayError("Wrong file format.");
                }
            }
        } catch (IOException e1) {
            this.view.displayError("IO Error: " + e1.getMessage());
        }
        this.model = new DrawNumberImpl(min, max, attempts);

    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
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
        new DrawNumberApp("config.yml");
    }

}
