package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ControllerImpl implements Controller {

    private final List<String> history;
    private String next;

    public ControllerImpl() {
        this.history = new ArrayList<>();
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
        this.next = next;
    }

    @Override
    public String getNextString() {
        if (this.next.isEmpty()) {
            return null;
        } else {
            return this.next;
        }
    }


    @Override
    public List<String> getHistory() {
        return List.copyOf(history);
    }


    @Override
    public void printCurrentString() {
        if (this.next == null) {
            throw new IllegalStateException();
        }
        this.history.add(this.next);
        System.out.println(this.next);
        this.next = "";
    }

}
