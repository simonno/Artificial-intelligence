import java.util.*;

/**
 * The type Astar.
 *
 * @param <E> the type parameter
 */
public class Astar<E> implements Searcher<E> {
    private Searchable<E> searchable;

    private class StateComparator implements Comparator<State<E>> {
        @Override
        public int compare(State<E> s1, State<E> s2) {
            double fValue1 = s1.getCost() + searchable.getHeuristics(s1);
            double fValue2 = s2.getCost() + searchable.getHeuristics(s2);
            if (fValue1 > fValue2) return 1;
            if (fValue1 < fValue2) return -1;
            return Integer.compare(s2.getDepth(), s1.getDepth());
        }
    }

    @Override
    public State<E> search(Searchable<E> searchable) {
        double max = Math.pow(searchable.getSize(), 2);
        this.searchable = searchable;
        StateComparator comparator = new StateComparator();
        LinkedList<State<E>> open = new LinkedList<>();

        open.add(searchable.getInitialState());

        int numberOfIteration = 0;
        while (!open.isEmpty() && numberOfIteration < max) {
            State<E> current = open.removeFirst();
            if (searchable.isGoal(current)) return current;

            for (State<E> successor : searchable.getSuccessors(current)) {
                successor.setCost(current.getCost() + successor.getCost());
                successor.setCameFrom(current);

                if (open.contains(successor)) {
                    int index = open.indexOf(successor);
                    State<E> dup = open.get(index);
                    if (dup.getCost() <= successor.getCost()) {
                        continue;
                    }
                    open.remove(index);
                }
                open.add(successor);
                open.sort(comparator);
            }
            numberOfIteration++;
        }
        return null;
    }
}

