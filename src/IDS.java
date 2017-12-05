import java.util.LinkedList;
import java.util.Queue;

/**
 * The IDS algorithm.
 *
 * @param <E> the type parameter
 */
public class IDS<E> implements Searcher<E> {
    private Queue<State<E>> queue;

    /**
     * Search the goal state by running IDS algorithm.
     *
     * @param searchable the searchable
     * @return the goal state, if null - no path to the goal from the initial.
     */
    @Override
    public State<E> search(Searchable<E> searchable) {
        double max = Math.pow(searchable.getSize(), 2);
        this.queue = new LinkedList<State<E>>();
        State<E> initial = searchable.getInitialState();
        State<E> found = null;
        for (int depth = 0; depth < max; depth++) {
            this.queue.add(initial);
            found = DLS(initial, depth, searchable);
            if (found != null) {
                return found;
            }
            this.queue.clear();
        }
        return null;
    }

    /**
     * Run DFS algorithm by recursion, limited to the max depth.
     *
     * @param s is the initial state.
     * @param depth the max depth for searching.
     * @param searchable the searchable
     * @return the goal state if found, null else.
     */
    private State<E> DLS(State<E> s, int depth, Searchable<E> searchable) {
        if (depth == 0 && searchable.isGoal(s)) {
            return s;
        }
        if (depth > 0) {
            for (State<E> successor : searchable.getSuccessors(s)) {
                if (!this.queue.contains(successor)) {
                    this.queue.add(successor);
                    State<E> found = DLS(successor, depth - 1, searchable);
                    if (found != null) {
                        State<E> cameFrom = found;
                        while (cameFrom.getCameFrom() != null) {
                            cameFrom = cameFrom.getCameFrom();
                        }
                        cameFrom.setCameFrom(s);
                        return found;
                    }
                    this.queue.remove(successor);
                }
            }
        }
        return null;
    }
}