import java.util.ArrayList;
import java.util.List;

/**
 * The type Grid.
 */
public class Grid implements Searchable<GridCell> {

    private ArrayList<ArrayList<GridCell>> grid;
    private final int rows;
    private final int columns;
    private GridCell start;
    private GridCell goal;

    /**
     * Instantiates a new Grid.
     *
     * @param grid        the grid
     * @param rows        the rows
     * @param columns     the columns
     * @param startRow    the start row
     * @param startColumn the start column
     * @param goalRow     the goal row
     * @param goalColumn  the goal column
     */
    public Grid(ArrayList<ArrayList<GridCell>> grid, int rows, int columns,
                int startRow, int startColumn, int goalRow, int goalColumn) {
        this.grid = grid;
        this.rows = rows;
        this.columns = columns;
        this.start = this.getCell(startRow, startColumn);
        this.goal = this.getCell(goalRow, goalColumn);
    }

    /**
     * Gets initial state.
     *
     * @return the initial state
     */
    @Override
    public State<GridCell> getInitialState() {
        return new State<GridCell>(this.start, this.start.getCost().doubleValue(), 0);
    }

    /**
     * Gets successors.
     * <p>
     * Return the successors of s, according to the clock direction, start with the right succesor.
     * Water cell cannot be a successor.
     * </p>
     *
     * @param s the state s
     * @return the successors of s
     */
    @Override
    public List<State<GridCell>> getSuccessors(State<GridCell> s) {
        ArrayList<State<GridCell>> successors = new ArrayList<State<GridCell>>();
        GridCell c = s.getElement();
        int row = c.getRow();
        int column = c.getColumn();
        int check1, check2 = -2, check3, check4;
        int depthSuccessors = s.getDepth() + 1;
        check1 = this.addSuccessor(successors, row, column + 1, depthSuccessors); //Right
        check4 = check1;
        if (check1 != 0) {
            check2 = this.addSuccessor(successors, row + 1, column + 1, depthSuccessors); //Right Down
        }
        check3 = this.addSuccessor(successors, row + 1, column, depthSuccessors); //Down
        if (check3 == 0 && check1 != 0 && check2 == 1) {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        check1 = check3;
        if (check1 != 0) {
            check2 = this.addSuccessor(successors, row + 1, column - 1, depthSuccessors); //Left Down
        }
        check3 = this.addSuccessor(successors, row, column - 1, depthSuccessors); //Left
        if (check3 == 0 && check1 != 0 && check2 == 1) {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        check1 = check3;
        if (check1 != 0) {
            check2 = this.addSuccessor(successors, row - 1, column - 1, depthSuccessors); //Left Up
        }
        check3 = this.addSuccessor(successors, row - 1, column, depthSuccessors); //Up
        if (check3 == 0 && check1 != 0 && check2 == 1) {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        if (check3 != 0 && check4 != 0) {
            this.addSuccessor(successors, row - 1, column + 1, depthSuccessors); //Right Up
        }
        return successors;
    }

    /**
     * Is goal boolean.
     *
     * @param s the state s
     * @return true if s is goal state, false else.
     */
    @Override
    public boolean isGoal(State<GridCell> s) {
        return s.getElement().getType() == GridCell.Type.GOAL;
    }

    /**
     * Gets heuristics - "air distance" between the s state to the goal state.
     *
     * @param s the state s
     * @return the heuristics of state s
     */
    @Override
    public double getHeuristics(State<GridCell> s) {
        return Math.max(Math.abs(this.goal.getRow() - s.getElement().getRow()),
                Math.abs(this.goal.getColumn() - s.getElement().getColumn()));
    }

    /**
     * Gets size.
     *
     * @return the size of the problem - number of rows multiply by number of column.
     */
    @Override
    public int getSize() {
        return this.rows * this.columns;
    }

    /**
     * Add a successor to the successors list.
     * <p>
     * Check if this is a valid successor to add.
     * Return:
     * 1 - the successor was added to the successors list.
     * 0 - the successor is a water cell - wasn't added.
     * -1 - the successor is out of the grid's bounds - wasn't added.
     * </p>
     *
     * @param successors is the list of the successors.
     * @param row        the row of the successor
     * @param column     the column of the successor
     * @param depth      the depth of the successor
     * @return 1, 0, -1 according to the rules which wrote upper.
     */
    private int addSuccessor(ArrayList<State<GridCell>> successors, int row, int column, int depth) {
        GridCell successor = this.getCell(row, column);
        if (successor != null) {
            if (successor.getType() != GridCell.Type.WATER) {
                successors.add(new State<GridCell>(successor, successor.getCost().doubleValue(), depth));
                return 1; // Ok
            }
            return 0; // a water cell
        }
        return -1; // out of grid's bounds
    }

    /**
     * Get cell.
     *
     * @param row    the row of the wanted cell.
     * @param column the column of the wanted cell.
     * @return the cell, or null case the cell is out of the grid's bounds.
     */
    private GridCell getCell(int row, int column) {
        if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
            return grid.get(row).get(column);
        }
        return null; // out of grid's bounds
    }
}
