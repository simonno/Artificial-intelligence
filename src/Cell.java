public class Cell {
    public enum Type {
        START(0), GOAL(1), ROAD(1), D(3), HILL(10), WATER(null);

        private final Integer cost;

        Type(Integer c) {
            this.cost = c;
        }

        public Integer getCost() {
            return cost;
        }
    }

    private Type type;
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Integer getCost() {
        return this.type.getCost();
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
