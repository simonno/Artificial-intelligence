import java.util.ArrayList;
import java.util.List;

public class Grid implements Searchable<Cell, Integer> {

    private ArrayList<ArrayList<State<Cell, Integer>>> grid;
    private final int rows;
    private final int columns;
    private State<Cell, Integer> start;

    public Grid(ArrayList<ArrayList<State<Cell, Integer>>> grid, int rows, int columns, int startRow, int startColumn) {
        this.grid = grid;
        this.rows = rows;
        this.columns = columns;
        this.start = this.getState(startRow, startColumn);
    }

    @Override
    public State<Cell, Integer> getInitialState() {
        return this.start;
    }

    @Override
    public List<State<Cell, Integer>> getSuccessors(State<Cell, Integer> s) {
        ArrayList<State<Cell, Integer>> successors = new ArrayList<State<Cell, Integer>>();
        Cell c = s.getElement();
        int row = c.getRow();
        int column = c.getColumn();
        int check1, check2 = -2, check3, check4;
        check1 = this.addSuccessor(successors, row, column + 1); //Right
        check4 = check1;
        if (check1 != 0) {
            check2 = this.addSuccessor(successors, row + 1, column + 1); //Right Down
        }
        check3 = this.addSuccessor(successors, row + 1, column); //Down
        if (check3 == 0  && check1 != 0 && check2 == 1)  {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        check1 = check3;
        if (check1 != 0) {
            check2 = this.addSuccessor(successors, row + 1, column - 1); //Left Down
        }
        check3 = this.addSuccessor(successors, row, column - 1); //Left
        if (check3 == 0  && check1 != 0 && check2 == 1)  {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        check1 = check3;
        if (check1 != 0) {
            check2 = this.addSuccessor(successors, row - 1, column - 1); //Left Up
        }
        check3 = this.addSuccessor(successors, row - 1, column); //Up
        if (check3 == 0  && check1 != 0 && check2 == 1)  {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        if (check3 != 0 && check4 != 0) {
            this.addSuccessor(successors, row - 1, column + 1); //Right Up
        }
        return successors;
    }

    private boolean addDiagonalSuccessor(ArrayList<State<Cell, Integer>> successors, int row, int column, Cell.Type t1, Cell.Type t2) {
        if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
            State<Cell, Integer> successor = this.getState(row, column);
            Cell.Type t = successor.getElement().getType();
            if (t != Cell.Type.WATER && t1 != Cell.Type.WATER && t2 != Cell.Type.WATER) {
                successors.add(successor);
                return true; // Ok
            }
        }
        return false; // out of grid's bounds or a water cell
    }

    private int addSuccessor(ArrayList<State<Cell, Integer>> successors, int row, int column) {
        if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
            State<Cell, Integer> successor = this.getState(row, column);
            if (successor.getElement().getType() != Cell.Type.WATER) {
                successors.add(successor);
                return 1; // Ok
            }
            return 0; // a water cell
        }
        return -1; // out of grid's bounds
    }


    @Override
    public boolean isGoal(State<Cell, Integer> s) {
        return s.getElement().getType() == Cell.Type.GOAL;
    }

    private State<Cell, Integer> getState(int row, int column) {
        return grid.get(row).get(column);
    }

}
