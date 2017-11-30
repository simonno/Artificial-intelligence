import java.util.ArrayList;
import java.util.List;

public class Grid implements Searchable<Cell> {

    private ArrayList<ArrayList<Cell>> grid;
    private final int rows;
    private final int columns;
    private Cell start;
    private Cell goal;

    public Grid(ArrayList<ArrayList<Cell>> grid, int rows, int columns,
                int startRow, int startColumn,int goalRow, int goalColumn) {
        this.grid = grid;
        this.rows = rows;
        this.columns = columns;
        this.start = this.getCell(startRow, startColumn);
        this.goal = this.getCell(goalRow, goalColumn);
    }

    @Override
    public State<Cell> getInitialState() {
        return  new State<Cell>(this.start, this.start.getCost().doubleValue(), 0);
    }

    @Override
    public List<State<Cell>> getSuccessors(State<Cell> s) {
        ArrayList<State<Cell>> successors = new ArrayList<State<Cell>>();
        Cell c = s.getElement();
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
        if (check3 == 0  && check1 != 0 && check2 == 1)  {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        check1 = check3;
        if (check1 != 0) {
            check2 = this.addSuccessor(successors, row + 1, column - 1, depthSuccessors); //Left Down
        }
        check3 = this.addSuccessor(successors, row, column - 1, depthSuccessors); //Left
        if (check3 == 0  && check1 != 0 && check2 == 1)  {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        check1 = check3;
        if (check1 != 0) {
            check2 = this.addSuccessor(successors, row - 1, column - 1, depthSuccessors); //Left Up
        }
        check3 = this.addSuccessor(successors, row - 1, column, depthSuccessors); //Up
        if (check3 == 0  && check1 != 0 && check2 == 1)  {
            int last = successors.size() - 1;
            successors.remove(last);
        }

        if (check3 != 0 && check4 != 0) {
            this.addSuccessor(successors, row - 1, column + 1, depthSuccessors); //Right Up
        }
        return successors;
    }

    @Override
    public boolean isGoal(State<Cell> s) {
        return s.getElement().getType() == Cell.Type.GOAL;
    }

    @Override
    public double getHeuristics(State<Cell> s) {
        int rowDiff = s.getElement().getRow() - this.goal.getRow();
        int columnDiff = s.getElement().getColumn() - this.goal.getColumn();
        return Math.sqrt(Math.pow(columnDiff, 2) + Math.pow(rowDiff, 2));
    }

    private int addSuccessor(ArrayList<State<Cell>> successors, int row, int column, int depth) {
        if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
            Cell successor = this.getCell(row, column);
            if (successor.getType() != Cell.Type.WATER) {
                successors.add(new State<Cell>(successor,successor.getCost().doubleValue(), depth));
                return 1; // Ok
            }
            return 0; // a water cell
        }
        return -1; // out of grid's bounds
    }

    private Cell getCell(int row, int column) {
        return grid.get(row).get(column);
    }
}
