package it.unibo.oop.lab.advanced;

import java.io.PrintStream;

public class DrawNumberViewSpecific implements DrawNumberView {
    
    private PrintStream output;

    public DrawNumberViewSpecific(final PrintStream output) {
        this.output = output;
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {

    }

    @Override
    public void start() {

    }

    @Override
    public void numberIncorrect() {
        output.println("Incorrect number. Try again!");
    }

    @Override
    public void result(final DrawResult res) {
        output.println("Result: " + res.getDescription());
    }

    @Override
    public void limitsReached() {
        output.println("You Lost");
    }

    @Override
    public void displayError(final String message) {
        output.println("An Error has occurred: " + message);
    }

}
