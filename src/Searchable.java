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
     * @param s the state s
     * @return the successors of s
     */
    List<State<E>> getSuccessors(State<E> s);

    /**
     * Is goal boolean.
     *
     * @param s the state s
     * @return true if s is goal state, false else.
     */
    boolean isGoal(State<E> s);

    /**
     * Gets heuristics.
     *
     * @param s the state s
     * @return the heuristics of state s
     */
    double getHeuristics(State<E> s);

    /**
     * Gets size.
     *
     * @return the size of the problem
     */
    int getSize();
}
