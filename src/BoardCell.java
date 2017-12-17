public class BoardCell extends Cell {
    public enum Type {
        BLACK('B'), WHITE('W'), EMPTY('E');

        private final Character t;

        Type(Character t) {
            this.t = t;
        }

        public Character getType() {
            return t;
        }
    }

    private Type type;


    /**
     * Instantiates a new BoardCell.
     *
     * @param row    the row
     * @param column the column
     */
    public BoardCell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public BoardCell(int row, int column, Type type) {
        this.type = type;
        this.row = row;
        this.column = column;
    }


    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

}
