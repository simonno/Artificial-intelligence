public class State<E, C> {

    private E element;
    private State<E, C> cameFrom;
    private C cost;

    public State(E element, C cost) {
        this.element = element;
        this.cost = cost;
    }

    public C getCost() {
        return cost;
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
}