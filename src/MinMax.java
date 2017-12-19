import static java.lang.Double.max;
import static java.lang.Double.min;

public class MinMax<E> implements Searcher<E> {
    private boolean isMaximizing;
    private int maxDepth;

    public MinMax(boolean isMaximizing, int maxDepth) {
        this.isMaximizing = isMaximizing;
        this.maxDepth = maxDepth;
    }

    public MinMax(boolean isMaximizing) {
        this.isMaximizing = isMaximizing;
        this.maxDepth = 3;
    }

    @Override
    public State<E> search(Searchable<E> searchable) {

        State<E> initialState = searchable.getInitialState();
        State<E> theBestNextState = null;
        if (this.isMaximizing) {
            double bestValue = Double.MIN_VALUE;
            for (State<E> successor : searchable.getSuccessors(initialState)) {
                double v = minimax(successor, this.maxDepth - 1, false, searchable);
                if (bestValue < v) {
                    theBestNextState = successor;
                    theBestNextState.setCost(v);
                    bestValue = v;
                }
            }
            return theBestNextState;

        } else {   // minimizing player
            double bestValue = Double.MAX_VALUE;
            for (State<E> successor : searchable.getSuccessors(initialState)) {
                double v = minimax(successor, this.maxDepth - 1, true, searchable);
                bestValue = min(bestValue, v);
                if (bestValue > v) {
                    theBestNextState = successor;
                    theBestNextState.setCost(v);
                    bestValue = v;
                }
            }
            return theBestNextState;
        }
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

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        if (maxDepth <= 0) {
            this.maxDepth = 3;
        }
        this.maxDepth = maxDepth;
    }

    public boolean isMaximizing() {
        return isMaximizing;
    }

    public void setMaximizing(boolean maximizing) {
        isMaximizing = maximizing;
    }

}
