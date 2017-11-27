public class State<E> {

    private E element;
    private State<E> cameFrom;
    private double cost;
//    private double heuristics;
//    private double fValue;

    public State(E element, double cost) {
        this.element = element;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public E getElement() {
        return element;
    }

    public void setCameFrom(State<E> cameFrom) {
        this.cameFrom = cameFrom;
    }

    public State<E> getCameFrom() {
        return cameFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        try {
            State<E> other = (State<E>) o;
            return this.element.equals(other.getElement());
        } catch (ClassCastException e) {
            return false;
        }
    }

//    public void setHeuristics(double heuristics) {
//        this.heuristics = heuristics;
//    }
//
//    public double getHeuristics() {
//        return heuristics;
//    }
//
//    public double getFValue() {
//        return fValue;
//    }
//
//    public void setFValue(double fValue) {
//        this.fValue = fValue;
//    }
}