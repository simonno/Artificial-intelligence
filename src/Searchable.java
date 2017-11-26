import java.util.List;

public interface Searchable {
    State getInitialState();
    List<State> getSuccessors(State s);
    boolean isGoal(State s);
}
