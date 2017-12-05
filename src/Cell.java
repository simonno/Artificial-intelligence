/**
 * The type Cell.
 */
public class Cell {
    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Start type.
         */
        START(0), /**
         * Goal type.
         */
        GOAL(0), /**
         * Road type.
         */
        ROAD(1), /**
         * D type.
         */
        D(3), /**
         * Hill type.
         */
        HILL(10), /**
         * Water type.
         */
        WATER(null);

        private final Integer cost;

        Type(Integer c) {
            this.cost = c;
        }

        /**
         * Gets cost.
         *
         * @return the cost
         */
        public Integer getCost() {
            return cost;
        }
    }

    private Type type;
    private int row;
    private int column;

    /**
     * Instantiates a new Cell.
     *
     * @param row    the row
     * @param column the column
     */
    public Cell(int row, int column) {
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

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public Integer getCost() {
        return this.type.getCost();
    }


    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets row.
     *
     * @param row the row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        try {
            Cell other = (Cell) o;
            if (this.getRow() == other.getRow() && this.getColumn() == other.getColumn()) {
                return true;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
