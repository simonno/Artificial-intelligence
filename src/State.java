public class State<E, C> {

    private E element;
    private E cameFrom;
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

    public void setCameFrom(E cameFrom) {
        this.cameFrom = cameFrom;
    }

    public E getCameFrom() {
        return cameFrom;
    }
}