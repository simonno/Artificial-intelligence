import java.util.LinkedList;
import java.util.List;

/**
 * The type Reversi.
 */
public class Reversi implements Searchable<Board> {

    private Board board;

    /**
     * Instantiates a new Reversi.
     *
     * @param board the board
     */
    public Reversi(Board board) {
        this.board = board;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets board.
     *
     * @param board the board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public State<Board> getInitialState() {
        return new State<Board>(board, 0);
    }

    @Override
    public List<State<Board>> getSuccessors(State<Board> s) {
        Board board = s.getElement();
        List<State<Board>> successors = new LinkedList<State<Board>>();
        List<Board> possiblePlacements = board.getPossiblePlacements();
        for (Board b : possiblePlacements) {
            successors.add(new State<Board>(b, 0));
        }
        return successors;
    }

    @Override
    public boolean isGoal(State<Board> s) {
        Board b = s.getElement();
        BoardCell.Type t = b.isWinning();
        return t != null;
    }

    @Override
    public double getHeuristics(State<Board> s) {
        Board b = s.getElement();
        BoardCell.Type t = b.isWinning();
        if (t == null) {
            int numberOfBlack = b.getNumberOfBlackCell();
            int numberOfWhite = b.getNumberOfWhiteCell();
            int numberOfBlackCellOnBounds = b.getNumberOfBlackCellOnBounds();
            int numberOfWhiteCellOnBounds = b.getNumberOfWhiteCellOnBounds();
            return numberOfBlack - numberOfWhite + numberOfBlackCellOnBounds - numberOfWhiteCellOnBounds;
        } else if (t == BoardCell.Type.BLACK) {
            return Double.MAX_VALUE;
        } else if (t == BoardCell.Type.WHITE) {
            return -Double.MAX_VALUE;
        } else { // tie
            return 0;
        }
    }

    @Override
    public int getSize() {
        return 0;
    }
}
