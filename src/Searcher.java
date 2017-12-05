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
     * @return the state
     */
    State<E> search(Searchable<E> searchable);
}