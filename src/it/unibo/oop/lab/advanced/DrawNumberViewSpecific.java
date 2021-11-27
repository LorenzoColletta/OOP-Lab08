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
        output.print("Incorrect number. Try again!");
    }

    @Override
    public void result(final DrawResult res) {
        output.print("Result: " + res.getDescription());
    }

    @Override
    public void limitsReached() {
        output.print("You Lost");
    }

    @Override
    public void displayError(final String message) {
        output.print("An Error has occurred: " + message);
    }

}
