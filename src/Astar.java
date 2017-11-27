import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Astar<E, C extends Comparable<C>> implements Searcher<E, C> {
    @Override
    public State<E, C> search(Searchable<E, C> searchable) {
        Queue<State<E, C>> open = new PriorityQueue<>(searchable.getComparator());
        List<State<E, C>> close = new ArrayList<>();

        State<E, C> start = searchable.getInitialState();
        start.setHeuristics(searchable.getHeuristics(start));
        start.setFValue(start.getHeuristics());
        open.add(start);

        while (open.isEmpty()) {
            State<E, C> current = open.poll();
            if (searchable.isGoal(current)) {
                return current;
            }

            for (State<E, C> successor : searchable.getSuccessors(current)) {
                C successorCurrentCost = searchable.getCurrentCost(successor, current);
                int compareResult = successor.getCost().compareTo(successorCurrentCost);
                if (open.contains(successor)) {
                    if (compareResult <= 0) {
                        continue;
                    }
                } else if (close.contains(successor)) {
                    if (compareResult <= 0) {
                        continue;
                    }
                    // move from close to open
                    close.remove(successor);
                    open.add(successor);
                } else  {
                    successor.setHeuristics(searchable.getHeuristics(successor));
                    open.add(successor);
                }
                successor.setCost(successorCurrentCost);
                successor.setCameFrom(current);
            }
            close.add(current);
        }
        return null;
    }
}
