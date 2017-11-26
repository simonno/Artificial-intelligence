import java.util.List;

public interface Searchable<E, C> {
    State<E, C> getInitialState();
    List<State<E, C>> getSuccessors(State<E, C> s);
    boolean isGoal(State<E, C> s);
}
