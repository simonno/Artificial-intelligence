import java.util.List;

public interface Searchable<E> {
    State<E> getInitialState();
    List<State<E>> getSuccessors(State<E> s);
    boolean isGoal(State<E> s);
    double getHeuristics(State<E> s);
}
