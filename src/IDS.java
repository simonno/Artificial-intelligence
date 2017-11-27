
public class IDS<E> implements Searcher<E> {

    @Override
    public State<E> search(Searchable<E> searchable) {
        State<E> initial = searchable.getInitialState();
        int depth = 0;
        State<E> found = null;
        while (true) {
            found = DLS(initial, depth, searchable);
            if (found != null) {
                return found;
            }
            depth++;
        }
    }

    private State<E> DLS(State<E> s, int depth, Searchable<E> searchable) {
        if (depth == 0 && searchable.isGoal(s)) {
            return s;
        }
        if (depth > 0) {
            for (State<E> successor : searchable.getSuccessors(s)) {
                State<E> found = DLS(successor, depth - 1, searchable);
                if (found != null) {
                    State<E> cameFrom = found;
                    while (cameFrom.getCameFrom() != null) {
                        cameFrom = cameFrom.getCameFrom();
                    }
                    cameFrom.setCameFrom(s);
                    return found;
                }
            }
        }
        return null;
    }

//    private State<E, C> DLSI(State<E, C> s, int depth, Searchable<E, C> searchable) {
//
//
//    }
}