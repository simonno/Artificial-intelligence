import java.util.Comparator;

public class State<E, C extends Comparable<C>> {

    private E element;
    private State<E, C> cameFrom;
    private C cost;
    private C heuristics;

    private C fValue;

    public State(E element, C cost) {
        this.element = element;
        this.cost = cost;
    }

    public C getCost() {
        return cost;
    }

    public void setCost(C cost) {
        this.cost = cost;
    }

    public E getElement() {
        return element;
    }

    public void setCameFrom(State<E, C> cameFrom) {
        this.cameFrom = cameFrom;
    }

    public State<E, C> getCameFrom() {
        return cameFrom;
    }

    public void setHeuristics(C heuristics) {
        this.heuristics = heuristics;
    }

    public C getHeuristics() {
        return heuristics;
    }

    public C getFValue() {
        return fValue;
    }

    public void setFValue(C fValue) {
        this.fValue = fValue;
    }
}