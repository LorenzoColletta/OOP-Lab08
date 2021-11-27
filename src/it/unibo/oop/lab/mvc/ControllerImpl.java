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

    @Override
    public void setNextString(String next) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getNextString() {
        // TODO Auto-generated method stub
        return null;
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
