import java.util.List;

/**
 * The interface Searchable.
 *
 * @param <E> the type parameter
 */
public interface Searchable<E> {
    /**
     * Gets initial state.
     *
     * @return the initial state
     */
    State<E> getInitialState();

    /**
     * Gets successors.
     *
     * @param s the s
     * @return the successors
     */
    List<State<E>> getSuccessors(State<E> s);

    /**
     * Is goal boolean.
     *
     * @param s the s
     * @return the boolean
     */
    boolean isGoal(State<E> s);

    /**
     * Gets heuristics.
     *
     * @param s the s
     * @return the heuristics
     */
    double getHeuristics(State<E> s);

    /**
     * Gets size.
     *
     * @return the size
     */
    int getSize();
}
