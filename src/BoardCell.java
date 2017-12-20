/**
 * The type Board cell.
 */
public class BoardCell extends Cell {
    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Black type.
         */
        BLACK('B'), /**
         * White type.
         */
        WHITE('W'), /**
         * Empty type.
         */
        EMPTY('E');

        private final Character t;

        Type(Character t) {
            this.t = t;
        }

        /**
         * Gets type.
         *
         * @return the type
         */
        public Character getType() {
            return t;
        }
    }

    private Type type;


    /**
     * Instantiates a new BoardCell.
     *
     * @param row    the row of the cell on the board
     * @param column the column of the cell in the board
     */
    public BoardCell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Instantiates a new Board cell.
     *
     * @param row    the row of the cell on the board
     * @param column the column of the cell in the board
     * @param type   the type of the cell
     */
    public BoardCell(int row, int column, Type type) {
        this.type = type;
        this.row = row;
        this.column = column;
    }


    /**
     * Sets type.
     *
     * @param type the type of the cell
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets type.
     *
     * @return the type of the cell
     */
    public Type getType() {
        return type;
    }

}
