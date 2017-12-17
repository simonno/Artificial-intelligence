/**
 * The type GridCell.
 */
public class GridCell extends Cell {
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


    /**
     * Instantiates a new GridCell.
     *
     * @param row    the row
     * @param column the column
     */
    public GridCell(int row, int column) {
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
     * Equals the objects.
     *
     * @param o is a object for comparing.
     * @return true if o is the same cell as this cell(according to the row and column), false else.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        try {
            GridCell other = (GridCell) o;
            return this.getRow() == other.getRow() && this.getColumn() == other.getColumn();
        } catch (ClassCastException e) {
            return false;
        }
    }

}
