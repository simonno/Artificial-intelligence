public interface Searcher<E, C extends Comparable<C>> {
    State<E, C> search(Searchable<E, C> searchable);
}