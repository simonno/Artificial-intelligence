import java.util.ArrayList;
import java.util.List;

public class Grid implements Searchable<Cell, Integer> {

    private ArrayList<ArrayList<State<Cell, Integer>>> grid;
    private final int rows;
    private final int columns;
    private int start_i;
    private final int start_j;
    private Cell start;

    public Grid(ArrayList<ArrayList<State<Cell, Integer>>> grid, int rows, int columns, int start_i, int start_j) {
        this.grid = grid;
        this.rows = rows;
        this.columns = columns;
        this.start_i = start_i;
        this.start_j = start_j;
    }

    @Override
    public State<Cell, Integer> getInitialState() {
        return this.getState(start_i, start_j);
    }

    @Override
    public List<State<Cell, Integer>> getSuccessors(State<Cell, Integer> s) {
        ArrayList<State<Cell, Integer>> successors = new ArrayList<State<Cell, Integer>>();
        Cell c = s.getElement();
        int row = c.getRow();
        int column = c.getColumn();
        this.addSuccessor(successors, row, column + 1); //Right
        this.addSuccessor(successors, row + 1, column + 1); //Right Down
        this.addSuccessor(successors, row + 1, column); //Down
        this.addSuccessor(successors, row + 1, column - 1); //Left Down
        this.addSuccessor(successors, row, column - 1); //Left
        this.addSuccessor(successors, row - 1, column - 1); //Left Up
        this.addSuccessor(successors, row - 1, column); //Up
        this.addSuccessor(successors, row - 1, column + 1); //Right Up
        return successors;
    }

    private boolean addSuccessor(ArrayList<State<Cell, Integer>> successors, int row, int column) {
        if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
            State<Cell, Integer> successor = this.getState(row, column);
            if (successor.getElement().getType() != Cell.Type.WATER) {
                successors.add(successor);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean isGoal(State<Cell, Integer> s) {
        return s.getElement().getType() == Cell.Type.GOAL;
    }

    private State<Cell, Integer> getState(int row, int column) {
//        ArrayList<State<Cell, Integer>> row1 = this.grid.get(row);
//        State<Cell, Integer> s = row1.get(column);
//        return s;
        return grid.get(row).get(column);
    }

}
