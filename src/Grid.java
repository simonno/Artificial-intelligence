import java.util.ArrayList;
import java.util.List;

public class Grid implements Searchable {

    private ArrayList<ArrayList<Cell>> grid;

    public Grid(ArrayList<ArrayList<Cell>> grid) {
        this.grid = grid;
    }

    @Override
    public State getInitialState() {
        return null;
    }

    @Override
    public List<State> getSuccessors(State s) {
        return null;
    }

    @Override
    public boolean isGoal(State s) {
        return false;
    }
}
