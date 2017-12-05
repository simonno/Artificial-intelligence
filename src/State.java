/**
 * The type State.
 *
 * @param <E> the type parameter
 */
public class State<E> {

    private E element;
    private State<E> cameFrom;
    private double cost;
    private int depth;

    /**
     * Instantiates a new State.
     *
     * @param element the element
     * @param cost    the cost
     */
    public State(E element, double cost) {
        this.element = element;
        this.cost = cost;
    }

    /**
     * Instantiates a new State.
     *
     * @param element the element
     * @param cost    the cost
     * @param depth   the depth
     */
    public State(E element, double cost, int depth) {
        this.element = element;
        this.cost = cost;
        this.depth = depth;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Gets element.
     *
     * @return the element
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets came from.
     *
     * @param cameFrom the came from
     */
    public void setCameFrom(State<E> cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * Gets came from.
     *
     * @return the came from
     */
    public State<E> getCameFrom() {
        return cameFrom;
    }

    /**
     * Gets depth.
     *
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets depth.
     *
     * @param depth the depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Equals the objects.
     *
     * @param o is a object for comparing.
     * @return true if o is the same state as this state (according to the elements of the states), false else.
     */
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