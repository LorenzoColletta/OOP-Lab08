package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ControllerImpl implements Controller {

    private final List<String> history;
    private final Queue<String> next;

    public ControllerImpl() {
        history = new ArrayList<>();
        next = new LinkedList<>();
    }

    /**
     * @param next
     *      next string to print
     */
    @Override
    public void setNextString(final String next) {
        if (next == null) {
            throw new NullPointerException();
        }
        this.next.add(next);
    }

    @Override
    public String getNextString() {
        if (this.next.isEmpty()) {
            return null;
        } else {
            return this.next.element();
        }
    }


    @Override
    public List<String> getHistory() {
        return List.copyOf(history);
    }


    @Override
    public void printCurrentString() {
        if (this.next.isEmpty()) {
            throw new IllegalStateException();
        }
        final String currentString = this.next.poll();
        System.out.println(currentString);
        this.history.add(currentString);

    }

}
