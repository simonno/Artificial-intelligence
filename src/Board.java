
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private ArrayList<ArrayList<BoardCell>> board;
    private int size;
    private int numberOfBlackCell;
    private int numberOfWhiteCell;
    private int numberOfEmptyCell;
    private int numberOfBlackCellOnBounds;
    private BoardCell.Type typeTurn;


    public Board(ArrayList<ArrayList<BoardCell>> board, int size, BoardCell.Type typeTurn) {
        this.board = board;
        this.size = size;
        this.typeTurn = typeTurn;
        this.numberOfBlackCell = 0;
        this.numberOfWhiteCell = 0;
        this.numberOfEmptyCell = 0;
        this.numberOfBlackCellOnBounds = 0;
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
            }

            if (this.getCell(this.size - 1, i).getType() == BoardCell.Type.BLACK) {
                this.numberOfBlackCellOnBounds++;
            }
        }

        for (int i = 1; i < size - 1; i++) {
            if (this.getCell(i, 0).getType() == BoardCell.Type.BLACK) {
                this.numberOfBlackCellOnBounds++;
            }

            if (this.getCell(i, this.size - 1).getType() == BoardCell.Type.BLACK) {
                this.numberOfBlackCellOnBounds++;
            }
        }

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
            } else { // draw
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


    public BoardCell.Type getTypeTurn() {
        return typeTurn;
    }

    public int getNumberOfBlackCellOnBounds() {
        return numberOfBlackCellOnBounds;
    }
}
