
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Board.
 */
public class Board {
    private ArrayList<ArrayList<BoardCell>> board;
    private int size;
    private int numberOfBlackCell;
    private int numberOfWhiteCell;
    private int numberOfEmptyCell;
    private int numberOfBlackCellOnBounds;
    private int numberOfWhiteCellOnBounds;
    private BoardCell.Type typeTurn;


    /**
     * Instantiates a new Board.
     *
     * @param board    the board
     * @param size     the size
     * @param typeTurn the type turn - the color of the player which is his turn.
     */
    public Board(ArrayList<ArrayList<BoardCell>> board, int size, BoardCell.Type typeTurn) {
        this.board = board;
        this.size = size;
        this.typeTurn = typeTurn;
        this.numberOfBlackCell = 0;
        this.numberOfWhiteCell = 0;
        this.numberOfEmptyCell = 0;
        this.numberOfBlackCellOnBounds = 0;
        this.numberOfWhiteCellOnBounds = 0;
        SetNumberOfCells();
    }

    /**
     * Instantiates a new Board.
     *
     * @param boardString the board string
     * @param size        the size
     */
    public Board(String boardString, int size) {
        this.board = this.Parser(boardString, size);
        this.size = size;
        SetNumberOfCells();

    }

    /**
     * Parser array list.
     *
     * @param boardString the board string
     * @param size        the size
     * @return the array list
     */
    public ArrayList<ArrayList<BoardCell>> Parser(String boardString, int size) {
        throw new UnsupportedOperationException();
    }

    /**
     * Initials the number of the type of the cells on board.
     */
    private void SetNumberOfCells() {
        for (ArrayList<BoardCell> row : this.board) {
            for (BoardCell cell : row) {
                switch (cell.getType()) {
                    case EMPTY:
                        this.numberOfEmptyCell++;
                        break;
                    case WHITE:
                        this.numberOfWhiteCell++;
                        break;
                    case BLACK:
                        this.numberOfBlackCell++;
                    default:
                        break;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            if (this.getCell(0, i).getType() == BoardCell.Type.BLACK) {
                this.numberOfBlackCellOnBounds++;
            } else if (this.getCell(0, i).getType() == BoardCell.Type.WHITE) {
                this.numberOfWhiteCellOnBounds++;
            }

            if (this.getCell(this.size - 1, i).getType() == BoardCell.Type.BLACK) {
                this.numberOfBlackCellOnBounds++;
            } else if (this.getCell(this.size - 1, i).getType() == BoardCell.Type.WHITE) {
                this.numberOfWhiteCellOnBounds++;
            }
        }

        for (int i = 1; i < size - 1; i++) {
            if (this.getCell(i, 0).getType() == BoardCell.Type.BLACK) {
                this.numberOfBlackCellOnBounds++;
            } else if (this.getCell(i, 0).getType() == BoardCell.Type.WHITE) {
                this.numberOfWhiteCellOnBounds++;
            }

            if (this.getCell(i, this.size - 1).getType() == BoardCell.Type.BLACK) {
                this.numberOfBlackCellOnBounds++;
            } else if (this.getCell(i, this.size - 1).getType() == BoardCell.Type.WHITE) {
                this.numberOfWhiteCellOnBounds++;
            }
        }

    }

    /**
     * Is winning.
     * <p>
     * check if it is a winning state.
     * case it is :
     * return BoardCell.Type.BLACK if the black player wins.
     * return BoardCell.Type.WHITE if the white player wins.
     * return BoardCell.Type.EMPTY for draw.
     * case it isn't: return null
     * </p>
     *
     * @return the board cell . type
     */
    public BoardCell.Type isWinning() {
        if (this.getNumberOfEmptyCell() == 0) { // the board is full.
            int numberOfBlack = this.getNumberOfBlackCell();
            int numberOfWhite = this.getNumberOfWhiteCell();
            int result = Integer.compare(numberOfBlack, numberOfWhite);
            if (result == 1) { // Black player wins.
                return BoardCell.Type.BLACK;
            } else if (result == -1) { // White player wins.
                return BoardCell.Type.WHITE;
            } else { // draw
                return BoardCell.Type.EMPTY;
            }

        }
        return null; // no winning.
    }


    /**
     * Gets number of empty cell.
     *
     * @return the number of empty cell
     */
    public int getNumberOfEmptyCell() {
        return numberOfEmptyCell;
    }

    /**
     * Gets number of black cell.
     *
     * @return the number of black cell
     */
    public int getNumberOfBlackCell() {
        return numberOfBlackCell;
    }

    /**
     * Gets number of white cell.
     *
     * @return the number of white cell
     */
    public int getNumberOfWhiteCell() {
        return numberOfWhiteCell;
    }

    /**
     * Gets possible placements.
     *
     * @param type the type
     * @return the possible placements
     */
    public List<Board> getPossiblePlacements(BoardCell.Type type) {
        this.printBoard();
        if (type == BoardCell.Type.EMPTY) { // can't place an empty cell on board.
            return null;
        }
        List<Board> possiblePlacements = new LinkedList<>();
        for (ArrayList<BoardCell> row : this.board) {
            for (BoardCell cell : row) {
                if (this.validForPlacement(cell)) {
                    possiblePlacements.add(this.placements(cell.getRow(), cell.getColumn(), type));
                }
            }
        }
        return possiblePlacements;
    }

    private void printBoard() {
        System.out.println("-----------------------------------");
        for (ArrayList<BoardCell> row : this.board) {
            StringBuilder r = new StringBuilder();
            for (BoardCell cell : row) {
                r.append(cell.getType().getType());
            }
            System.out.println(r);

        }
        System.out.println("-----------------------------------");
    }

    /**
     * Placement a pawn on board.
     *
     * @param row    the row of the cell on board
     * @param column the column of the cell on board
     * @param type   the type of the player to add.
     * @return the new board after the placement.
     */
    private Board placements(int row, int column, BoardCell.Type type) {
        ArrayList<ArrayList<BoardCell>> copyBoard = this.copyBoard();
        copyBoard.get(row).get(column).setType(type);
        for (int i = column + 1; i < this.size; i++) { // right
            BoardCell cell = copyBoard.get(row).get(i);
            if (cell.getType() == BoardCell.Type.EMPTY) {
                break;
            } else if (cell.getType() == type) {
                for (int j = i; j > column; j--) {
                    copyBoard.get(row).get(j).setType(type);
                }
                break;
            }
        }


        for (int r = row + 1, c = column + 1; (r < this.size) && (c < this.size); r++, c++) { // right down
            BoardCell cell = copyBoard.get(r).get(c);
            if (cell.getType() == BoardCell.Type.EMPTY) {
                break;
            } else if (cell.getType() == type) {
                for (int r2 = r, c2 = c; (r2 > row) && (c2 > column); r2--, c2--) {
                    copyBoard.get(r2).get(c2).setType(type);
                }
                break;
            }
        }

        for (int i = row + 1; i < this.size; i++) { // down
            BoardCell cell = copyBoard.get(i).get(column);
            if (cell.getType() == BoardCell.Type.EMPTY) {
                break;
            } else if (cell.getType() == type) {
                for (int j = i; j > row; j--) {
                    copyBoard.get(j).get(column).setType(type);
                }
                break;
            }
        }

        for (int r = row + 1, c = column - 1; (r < this.size) && (c >= 0); r++, c--) { // left down
            BoardCell cell = copyBoard.get(r).get(c);
            if (cell.getType() == BoardCell.Type.EMPTY) {
                break;
            } else if (cell.getType() == type) {
                for (int r2 = r, c2 = c; (r2 > row) && (c2 < column); r2--, c2++) {
                    copyBoard.get(r2).get(c2).setType(type);
                }
                break;
            }
        }


        for (int i = column - 1; i >= 0; i--) { // left
            BoardCell cell = copyBoard.get(row).get(i);
            if (cell.getType() == BoardCell.Type.EMPTY) {
                break;
            } else if (cell.getType() == type) {
                for (int j = i; j < column; j++) {
                    copyBoard.get(row).get(j).setType(type);
                }
                break;
            }
        }

        for (int r = row - 1, c = column - 1; (r >= 0) && (c >= 0); r--, c--) { // left up
            BoardCell cell = copyBoard.get(r).get(c);
            if (cell.getType() == BoardCell.Type.EMPTY) {
                break;
            } else if (cell.getType() == type) {
                for (int r2 = r, c2 = c; (r2 < row) && (c2 < column); r2++, c2++) {
                    copyBoard.get(r2).get(c2).setType(type);
                }
                break;
            }
        }

        for (int i = row - 1; i >= 0; i--) { // up
            BoardCell cell = copyBoard.get(i).get(column);
            if (cell.getType() == BoardCell.Type.EMPTY) {
                break;
            } else if (cell.getType() == type) {
                for (int j = i; j < row; j++) {
                    copyBoard.get(j).get(column).setType(type);
                }
                break;
            }
        }

        for (int r = row - 1, c = column + 1; (r >= 0) && (c < this.size); r--, c++) { // right up
            BoardCell cell = copyBoard.get(r).get(c);
            if (cell.getType() == BoardCell.Type.EMPTY) {
                break;
            } else if (cell.getType() == type) {
                for (int r2 = r, c2 = c; (r2 < row) && (c2 > column); r2++, c2--) {
                    copyBoard.get(r2).get(c2).setType(type);
                }
                break;
            }
        }

        if (type == BoardCell.Type.BLACK) {
            return new Board(copyBoard, this.size, BoardCell.Type.WHITE);
        } else {
            return new Board(copyBoard, this.size, BoardCell.Type.BLACK);
        }
    }

    /**
     * Copy the board of this class.
     *
     * @return the copy of the board
     */
    private ArrayList<ArrayList<BoardCell>> copyBoard() {
        ArrayList<ArrayList<BoardCell>> copy = new ArrayList<>();
        for (ArrayList<BoardCell> row : board) {
            ArrayList<BoardCell> copyRow = new ArrayList<>();
            for (BoardCell cell : row) {
                copyRow.add(new BoardCell(cell.getRow(), cell.getColumn(), cell.getType()));
            }
            copy.add(copyRow);
        }
        return copy;
    }

    /**
     * Check if the given cell is valid to placement a pawn.
     *
     * @param cell the given cell.
     * @return true if valid, false else.
     */
    private boolean validForPlacement(BoardCell cell) {
        if (cell.getType() == BoardCell.Type.EMPTY) {
            List<BoardCell> successors = this.getSuccessors(cell);
            for (BoardCell successor : successors) {
                if (successor.getType() != BoardCell.Type.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the successors of the given cell.
     *
     * @param cell the given cell
     * @return list of the successors
     */
    private List<BoardCell> getSuccessors(BoardCell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();
        List<BoardCell> successors = new LinkedList<>();
        this.addSuccessor(row, column + 1, successors); // right
        this.addSuccessor(row + 1, column + 1, successors); // right down
        this.addSuccessor(row + 1, column, successors); // down
        this.addSuccessor(row + 1, column - 1, successors); // left down
        this.addSuccessor(row, column - 1, successors); // left
        this.addSuccessor(row - 1, column - 1, successors); // left up
        this.addSuccessor(row - 1, column, successors); // up
        this.addSuccessor(row - 1, column + 1, successors); // right up
        return successors;
    }

    /**
     * Add successor.
     * <p>
     * add the spcific cell on the row and column which given if it is in the board's bound
     * </p>
     *
     * @param row        the row of the cell on board
     * @param column     the column of the cell on board
     * @param successors the list to add the cell.
     * @return true - adding succeed, false else.
     */
    private boolean addSuccessor(int row, int column, List<BoardCell> successors) {
        BoardCell successor = getCell(row, column);
        if (successor != null) {
            successors.add(successor);
            return true;
        }
        return false;
    }


    /**
     * Get cell.
     *
     * @param row    the row of the wanted cell.
     * @param column the column of the wanted cell.
     * @return the cell, or null case the cell is out of the grid's bounds.
     */
    private BoardCell getCell(int row, int column) {
        if (row >= 0 && row < this.size && column >= 0 && column < this.size) {
            return this.board.get(row).get(column);
        }
        return null; // out of grid's bounds
    }


    /**
     * Gets type turn.
     *
     * @return the type turn
     */
    public BoardCell.Type getTypeTurn() {
        return typeTurn;
    }

    /**
     * Gets number of black cell on bounds.
     *
     * @return the number of black cell on bounds
     */
    public int getNumberOfBlackCellOnBounds() {
        return numberOfBlackCellOnBounds;
    }

    /**
     * Gets number of white cell on bounds.
     *
     * @return the number of white cell on bounds
     */
    public int getNumberOfWhiteCellOnBounds() {
        return numberOfWhiteCellOnBounds;
    }
}
