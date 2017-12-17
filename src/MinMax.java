import static java.lang.Double.max;
import static java.lang.Double.min;

public class MinMax<E> implements Searcher<E> {
    @Override
    public State<E> search(Searchable<E> searchable) {
        double value = minimax(searchable.getInitialState(), 3, true, searchable);
        return new State<E>(null, value);
    }

    private double minimax(State<E> state, int depth, boolean maximizingPlayer, Searchable<E> searchable) {
        if (depth == 0 || searchable.isGoal(state)) {
            return searchable.getHeuristics(state);
        }

        if (maximizingPlayer) {
            double bestValue = Double.MIN_VALUE;
            for (State<E> successor : searchable.getSuccessors(state)) {
                double v = minimax(successor, depth - 1, false, searchable);
                bestValue = max(bestValue, v);
            }
            return bestValue;

        } else {   // minimizing player
            double bestValue = Double.MAX_VALUE;
            for (State<E> successor : searchable.getSuccessors(state)) {
                double v = minimax(successor, depth - 1, true, searchable);
                bestValue = min(bestValue, v);
            }
            return bestValue;
        }
    }

}
