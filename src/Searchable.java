import java.util.Comparator;
import java.util.List;

public interface Searchable<E, C extends Comparable<C>> {
    State<E, C> getInitialState();
    List<State<E, C>> getSuccessors(State<E, C> s);
    boolean isGoal(State<E, C> s);
    C getHeuristics(State<E, C> s);
    C getCurrentCost(State<E, C> s, State<E, C> cameFrom);
    C sumCost(C c1, C c2);
    Comparator<State<E,C>> getComparator();
}
