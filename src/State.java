import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.LinkedList;
import java.util.List;

public class State {
    private List<State> successors;
    private final int cost;
    private final Type type;

    public Type getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public List<State> getSuccessors() {
        return successors;
    }

    public boolean addSuccessor(State successor) {
        return this.successors.add(successor);
    }

    public boolean removeSuccessor(State successor) {
        return this.successors.remove(successor);
    }

    public enum Type {
        START, GOAL, ROAD, D, HILL, WATER
    }

    public State(Type type) {
        this.successors = new LinkedList<>();
        this.type = type;
        switch (type) {
            case D:
                this.cost = 3;
                break;
            case GOAL:
                this.cost = 1;
                break;
            case HILL:
                this.cost = 10;
                break;
            case ROAD:
                this.cost = 1;
                break;
            case START:
                this.cost = 1;
                break;
            case WATER:
                this.cost = -1;
                break;
            default:
                this.cost = -1;
        }

    }



}
