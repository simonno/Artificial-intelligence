public class State<E> {

    private E element;
    private State<E> cameFrom;
    private double cost;
    private int depth;

    public State(E element, double cost) {
        this.element = element;
        this.cost = cost;
    }

    public State(E element, double cost, int depth) {
        this.element = element;
        this.cost = cost;
        this.depth = depth;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public E getElement() {
        return element;
    }

    public void setCameFrom(State<E> cameFrom) {
        this.cameFrom = cameFrom;
    }

    public State<E> getCameFrom() {
        return cameFrom;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        try {
            State<E> other = (State<E>) o;
            return this.element.equals(other.getElement());
        } catch (ClassCastException e) {
            return false;
        }
    }
}