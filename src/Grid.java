import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Grid implements Searchable<Cell, Double> {

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
    public State<Cell, Double> getInitialState() {
        return new State<Cell, Double>(this.start, this.start.getCost().doubleValue());
    }

    @Override
    public List<State<Cell, Double>> getSuccessors(State<Cell, Double> s) {
        ArrayList<State<Cell, Double>> successors = new ArrayList<State<Cell, Double>>();
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

    private int addSuccessor(ArrayList<State<Cell, Double>> successors, int row, int column) {
        if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
            Cell successor = this.getCell(row, column);
            if (successor.getType() != Cell.Type.WATER) {
                successors.add(new State<Cell, Double>(successor,successor.getCost().doubleValue()));
                return 1; // Ok
            }
            return 0; // a water cell
        }
        return -1; // out of grid's bounds
    }

    @Override
    public boolean isGoal(State<Cell, Double> s) {
        return s.getElement().getType() == Cell.Type.GOAL;
    }

    @Override
    public Double getHeuristics(State<Cell, Double> s) {
        int rowDiff = s.getElement().getRow() - this.goal.getRow();
        int columnDiff = s.getElement().getColumn() - this.goal.getColumn();
        return Math.sqrt(Math.pow(columnDiff, 2) + Math.pow(rowDiff, 2));
    }

    @Override
    public Double getCurrentCost(State<Cell, Double> s, State<Cell, Double> cameFrom) {
       return cameFrom.getCost() + s.getElement().getCost();
    }

    @Override
    public Double sumCost(Double c1, Double c2) {
        return c1 + c2;
    }

    @Override
    public Comparator<State<Cell, Double>> getComparator() {
        return new StateComparator();
    }

    private Cell getCell(int row, int column) {
        return grid.get(row).get(column);
    }

    public class StateComparator implements Comparator<State<Cell, Double>> {
        @Override
        public int compare(State<Cell, Double> s1, State<Cell, Double> s2) {
            if (s1.getFValue() > s2.getFValue()) return 1;
            if (s2.getFValue() > s1.getFValue()) return -1;
            return 0;
        }
    }
}
