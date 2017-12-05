/**
 * The interface Searcher.
 *
 * @param <E> the type parameter
 */
public interface Searcher<E> {
    /**
     * Search state.
     *
     * @param searchable the searchable
     * @return the goal state, if null - no path to the goal from the initial.
     */
    State<E> search(Searchable<E> searchable);
}