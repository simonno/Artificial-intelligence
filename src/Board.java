import java.util.ArrayList;
import java.util.List;

public class Board {
    private ArrayList<ArrayList<BoardCell>> board;
    private int size;
    private int numberOfBlackCell;
    private int numberOfWhiteCell;
    private int numberOfEmptyCell;

    public Board(ArrayList<ArrayList<BoardCell>> board, int size) {
        this.board = board;
        this.size = size;
        SetNumberOfCells();
    }

    public Board(String boardString, int size) {
        this.board = this.Parser(boardString, size);
        this.size = size;
        SetNumberOfCells();

    }

    public ArrayList<ArrayList<BoardCell>> Parser(String boardString, int size) {
        throw new UnsupportedOperationException();
    }

    private void SetNumberOfCells() {
        int numberOfBlackCell = 0;
        int numberOfWhiteCell = 0;
        int numberOfEmptyCell = 0;
        for (ArrayList<BoardCell> row : board) {
            for (BoardCell cell : row) {
                switch (cell.getType()) {
                    case EMPTY:
                        numberOfEmptyCell++;
                        break;
                    case WHITE:
                        numberOfWhiteCell++;
                        break;
                    case BLACK:
                        numberOfBlackCell++;
                    default:
                        break;
                }
            }
        }
        this.numberOfBlackCell = numberOfBlackCell;
        this.numberOfWhiteCell = numberOfWhiteCell;
        this.numberOfEmptyCell = numberOfEmptyCell;
    }

    public BoardCell.Type isWinning() {
        if (this.getNumberOfEmptyCell() == 0) { // the board is full.
            int numberOfBlack = this.getNumberOfBlackCell();
            int numberOfWhite = this.getNumberOfWhiteCell();
            int result = Integer.compare(numberOfBlack, numberOfWhite);
            if (result == 1) { // Black player wins.
                return BoardCell.Type.BLACK;
            } else if (result == -1) { // White player wins.
                return BoardCell.Type.WHITE;
            } else { // tie
                return BoardCell.Type.EMPTY;
            }

        }
        return null; // no winning.
    }


    public int getNumberOfEmptyCell() {
        return numberOfEmptyCell;
    }

    public int getNumberOfBlackCell() {
        return numberOfBlackCell;
    }

    public int getNumberOfWhiteCell() {
        return numberOfWhiteCell;
    }

    public List<Board> getPossiblePlacements() {
        return null;
    }
}
