import java.util.List;

public class IDS<E, C> implements Searcher<E, C> {

    @Override
    public State<E, C> search(Searchable<E, C> searchable) {
        State<E, C> initial = searchable.getInitialState();
        int depth = 0;
        State<E, C> found = null;
        while (true) {
            found = DLS(initial, depth, searchable);
            if (found != null) {
                return found;
            }
            depth++;
        }
    }

    private State<E, C> DLS(State<E, C> s, int depth, Searchable<E, C> searchable) {
        if (depth == 0 && searchable.isGoal(s)) {
            return s;
        }
        if (depth > 0) {
            for (State<E, C> successor : searchable.getSuccessors(s)) {
                State<E, C> found = DLS(successor, depth -1, searchable);
                if (found != null){
                    State<E, C> cameFrom = found;
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
}
