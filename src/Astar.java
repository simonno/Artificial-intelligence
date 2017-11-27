import java.util.*;

public class Astar<E> implements Searcher<E> {
    private Searchable<E> searchable;

    private class StateComparator implements Comparator<State<E>> {
        @Override
        public int compare(State<E> s1, State<E> s2) {
            double fValue1 = s1.getCost() + searchable.getHeuristics(s1);
            double fValue2 = s2.getCost() + searchable.getHeuristics(s2);
            return Double.compare(fValue1, fValue2);
        }
    }


    @Override
    public State<E> search(Searchable<E> searchable) {
        this.searchable = searchable;
        StateComparator comparator = new StateComparator();
        LinkedList<State<E>> open = new LinkedList<>();

        open.add(searchable.getInitialState());

        while (!open.isEmpty()) {
            State<E> current = open.removeFirst();
            if (searchable.isGoal(current)) return current;

            for (State<E> successor : searchable.getSuccessors(current)) {
                successor.setCost(current.getCost() + successor.getCost());
                successor.setCameFrom(current);

                if (open.contains(successor)) {
                    int index = open.indexOf(successor);
                    State<E> dup = open.get(index);
                    if (dup.getCost() < successor.getCost()) {
                        continue;
                    }
                    open.remove(index);
                }
                open.add(successor);
                open.sort(comparator);
            }
        }
        return null;
    }
}
