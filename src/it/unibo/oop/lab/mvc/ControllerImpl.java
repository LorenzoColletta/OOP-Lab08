package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ControllerImpl implements Controller {

    private List<String> history;
    private Queue<String> next;

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void printCurrentString() {
        // TODO Auto-generated method stub

    }

}
