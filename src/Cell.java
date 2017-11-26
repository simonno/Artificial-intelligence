public enum Cell {
    START(1), GOAL(1), ROAD(1), D(3), HILL(10), WATER(null);

    private Integer cost;

    Cell(Integer c) {
        cost = c;
    }

    public Integer getCost() {
        return cost;
    }
}
