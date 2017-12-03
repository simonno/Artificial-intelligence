import java.util.LinkedList;
import java.util.Queue;

public class IDS<E> implements Searcher<E> {
    private Queue<State<E>> queue;

    @Override
    public State<E> search(Searchable<E> searchable) {
        this.queue = new LinkedList<State<E>>();
        State<E> initial = searchable.getInitialState();
        int depth = 0;
        State<E> found = null;
        while (true) {
            this.queue.add(initial);
            found = DLS(initial, depth, searchable);
            if (found != null) {
                return found;
            }
            this.queue.clear();
            depth++;
        }
    }

    private State<E> DLS(State<E> s, int depth, Searchable<E> searchable) {
        if (depth == 0 && searchable.isGoal(s)) {
            return s;
        }
        if (depth > 0) {
            for (State<E> successor : searchable.getSuccessors(s)) {
                if (!this.queue.contains(successor)) {
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
            this.queue.remove(s);
        }
        this.queue.remove(s);
        return null;
    }
}