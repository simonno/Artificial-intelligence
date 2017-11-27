public interface Searcher<E> {
    State<E> search(Searchable<E> searchable);
}