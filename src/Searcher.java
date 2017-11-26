public interface Searcher<E,C> {
    State<E,C> search(Searchable<E, C> searchable);
}