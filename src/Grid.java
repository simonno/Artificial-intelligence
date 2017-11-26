import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid implements Searchable<Cell, Integer> {

    private ArrayList<ArrayList<State<Cell, Integer>>> grid;
    private int start_i;
    private final int start_j;
    private Cell start;

    public Grid(ArrayList<ArrayList<State<Cell, Integer>>> grid,int start_i, int start_j) {
        this.grid = grid;
        this.start_i = start_i;
        this.start_j = start_j;
    }

    @Override
    public State<Cell, Integer> getInitialState() {
        return grid.get(start_i).get(start_j);
    }

    @Override
    public List<State<Cell, Integer>> getSuccessors(State<Cell, Integer> s) {
        ArrayList<State<Cell, Integer>> successors = new ArrayList<State<Cell, Integer>>();

    }

    @Override
    public boolean isGoal(State<Cell, Integer> s) {
        return s.getElement() == Cell.GOAL;
    }
}
